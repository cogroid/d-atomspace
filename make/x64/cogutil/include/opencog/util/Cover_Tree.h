/*
 * Copyright (C) 2011 by OpenCog Foundation
 * All Rights Reserved
 *
 * Written by David Crane <dncrane@gmail.com>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License v3 as
 * published by the Free Software Foundation and including the exceptions
 * at http://opencog.org/wiki/Licenses
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program; if not, write to:
 * Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

#ifndef _COVER_TREE_H
#define _COVER_TREE_H

#include <float.h>

#include <algorithm>
#include <cmath>
#include <iostream>
#include <map>
#include <set>
#include <utility>
#include <vector>

/** \addtogroup grp_cogutil
 *  @{
 */

//! Cover Tree. Allows for insertion, removal, and k-nearest-neighbor queries.
/**
 * https://github.com/DNCrane/Cover-Tree
 *
 * The user should define double Point::distance(const Point& p) and
 * bool Point::operator==(const Point& p), where
 * p1.distance(p2)==0 doesn't necessarily mean that p1==p2).
 *
 * For example, a point could consist of a vector and a string
 * name, where their distance measure is simply euclidean distance but to be
 * equal they must have the same name in addition to having distance 0.
 *
 * This is a C++ implementation of the cover tree datastructure. Implements the
 * cover tree algorithms for insert, removal, and k-nearest-neighbor search.
 *
 * Relevant links:
 * - <a href="https://secure.wikimedia.org/wikipedia/en/wiki/Cover_tree">Wikipedia's page</a>
 * on cover trees.
 * - John Langford's (one of the inventors of cover trees)
 * <a href="http://hunch.net/~jl/projects/cover_tree/cover_tree.html">page</a>
 * on cover trees with links to papers.
 *
 * To use the Cover Tree, you must implement your own Point class. CoverTreePoint
 * is provided for testing and as an example. Your Point class must implement the
 * following functions:
 *
 * @code
 * double YourPoint::distance(const YourPoint& p);
 * bool YourPoint::operator==(const YourPoint& p);
 * and optionally (for debugging/printing only):
 * void YourPoint::print();
 * @endcode
 *
 * The distance function must be a Metric, meaning (from Wikipedia):
 * 1: d(x, y) = 0   if and only if   x = y
 * 2: d(x, y) = d(y, x)     (symmetry)
 * 3: d(x, z) =< d(x, y) + d(y, z)     (subadditivity / triangle inequality).
 *
 * See https://secure.wikimedia.org/wikipedia/en/wiki/Metric_%28mathematics%29
 * for details.
 *
 * Actually, 1 does not exactly need to hold for this implementation; you can
 * provide, for example, names for your points which are unrelated to distance
 * but important for equality. You can insert multiple points with distance 0 to
 * each other and the tree will keep track of them, but you cannot insert multiple
 * points that are equal to each other; attempting to insert a point that
 * already exists in the tree will not alter the tree at all.
 *
 * If you do not want to allow multiple nodes with distance 0, then just make
 * your equality operator always return true when distance is 0.
 *
 */
template<class Point>
class CoverTree
{
    /**
     * Cover tree node. Consists of arbitrarily many points P, as long as
     * they have distance 0 to each other. Keeps track of its children.
     */
    class CoverTreeNode
    {
    private:
        //_childMap[i] is a vector of the node's children at level i
        std::map<int,std::vector<CoverTreeNode*> > _childMap;
        //_points is all of the points with distance 0 which are not equal.
        std::vector<Point> _points;
    public:
        CoverTreeNode(const Point& p);
        /**
         * Returns the children of the node at level i. Note that this means
         * the children exist in cover set i-1, not level i.
         *
         * Does not include the node itself, though technically every node
         * has itself as a child in a cover tree.
         */
        std::vector<CoverTreeNode*> get_children(int level) const;
        void add_child(int level, CoverTreeNode* p);
        void remove_child(int level, CoverTreeNode* p);
        void add_point(const Point& p);
        void remove_point(const Point& p);
        const std::vector<Point>& get_points() { return _points; }
        double distance(const CoverTreeNode& p) const;

        bool is_single() const;
        bool has_point(const Point& p) const;

        const Point& get_point() const;

        /**
         * Return every child of the node from any level. This is handy for
         * the destructor.
         */
        std::vector<CoverTreeNode*> get_all_children() const;
    }; // CoverTreeNode class

 private:
    typedef std::pair<double, CoverTreeNode*> distNodePair;

    CoverTreeNode* _root;
    unsigned int _numNodes;
    int _maxLevel;//base^_maxLevel should be the max distance
                  //between any 2 points
    int _minLevel;//A level beneath which there are no more new nodes.

    std::vector<CoverTreeNode*>
        k_nearest_nodes(const Point& p, const unsigned int& k) const;
    /**
     * Recursive implementation of the insert algorithm (see paper).
     */
    bool insert_rec(const Point& p,
                    const std::vector<distNodePair>& Qi,
                    const int& level);

    /**
     * Finds the node in Q with the minimum distance to p. Returns a
     * pair consisting of this node and the distance.
     */
    distNodePair distance(const Point& p,
                          const std::vector<CoverTreeNode*>& Q);


    void remove_rec(const Point& p,
                    std::map<int,std::vector<distNodePair> >& coverSets,
                    int level,
                    bool& multi);

 public:
    const double base;

    /**
     * Constructs a cover tree which begins with all points in points.
     *
     * maxDist should be the maximum distance that any two points
     * can have between each other. IE p.distance(q) < maxDist for all
     * p,q that you will ever try to insert. The cover tree may be invalid
     * if an inaccurate maxDist is given.
     */

    CoverTree(const double& maxDist,
              const std::vector<Point>& points=std::vector<Point>());
    ~CoverTree();

    /**
     * Just for testing/debugging. Returns true iff the cover tree satisfies the
     * the covering tree invariants (every node in level i is greater than base^i
     * distance from every other node, and every node in level i is less than
     * or equal to base^i distance from its children). See the cover tree
     * papers for details.
     */
    bool is_valid_tree() const;

    /**
     * Insert newPoint into the cover tree. If newPoint is already present,
     * (that is, newPoint==p for some p already in the tree), then the tree
     * is unchanged. If p.distance(newPoint)==0.0 but newPoint!=p, then
     * newPoint WILL be inserted and both points may be returned in k-nearest-
     * neighbor searches.
     */
    void insert(const Point& newPoint);

    /**
     * Remove point p from the cover tree. If p is not present in the tree,
     * it will remain unchanged. Otherwise, this will remove exactly one
     * point q from the tree satisfying p==q.
     */
    void remove(const Point& p);

    /**
     * Returns the k nearest points to p in order (the 0th element of the vector
     * is closest to p, 1th is next, etc). It may return greater than k points
     * if there is a tie for the kth place.
     */
    std::vector<Point> k_nearest_neighbors(const Point& p, const unsigned int& k) const;

    CoverTreeNode* get_root() const;

    /**
     * Print the cover tree.
     */
    void print() const;
}; // CoverTree class

template<class Point>
CoverTree<Point>::CoverTree(const double& maxDist,
                            const std::vector<Point>& points) : base(2.0)
{
    _root=NULL;
    _numNodes=0;
    _maxLevel=ceilf(log(maxDist)/log(base));
    _minLevel=_maxLevel-1;
    typename std::vector<Point>::const_iterator it;
    for(it=points.begin(); it!=points.end(); ++it) {
        this->insert(*it);
    }
}

template<class Point>
CoverTree<Point>::~CoverTree()
{
    if(_root==NULL) return;
    //Get all of the root's children (from any level),
    //delete the root, repeat for each of the children
    std::vector<CoverTreeNode*> nodes;
    nodes.push_back(_root);
    while(!nodes.empty()) {
        CoverTreeNode* byeNode = nodes[0];
        nodes.erase(nodes.begin());
        std::vector<CoverTreeNode*> children = byeNode->get_all_children();
        nodes.insert(nodes.begin(),children.begin(), children.end());
        //std::cout << _numNodes << "\n";
        delete byeNode;
        //_numNodes--;
    }
}

template<class Point>
std::vector<typename CoverTree<Point>::CoverTreeNode*>
CoverTree<Point>::k_nearest_nodes(const Point& p, const unsigned int& k) const
{
    if(_root==NULL) return std::vector<CoverTreeNode*>();
    //maxDist is the kth nearest known point to p, and also the farthest
    //point from p in the set minNodes defined below.
    double maxDist = p.distance(_root->get_point());
    //minNodes stores the k nearest known points to p.
    std::set<distNodePair> minNodes;

    minNodes.insert(std::make_pair(maxDist,_root));
    std::vector<distNodePair> Qj(1,std::make_pair(maxDist,_root));
    for(int level = _maxLevel; level>=_minLevel;level--) {
        typename std::vector<distNodePair>::const_iterator it;
        int size = Qj.size();
        for(int i=0; i<size; i++) {
            std::vector<CoverTreeNode*> children =
                Qj[i].second->get_children(level);
            typename std::vector<CoverTreeNode*>::const_iterator it2;
            for(it2=children.begin(); it2!=children.end(); ++it2) {
                double d = p.distance((*it2)->get_point());
                if(d < maxDist || minNodes.size() < k) {
                    minNodes.insert(std::make_pair(d,*it2));
                    //--minNodes.end() gives us an iterator to the greatest
                    //element of minNodes.
                    if(minNodes.size() > k) minNodes.erase(--minNodes.end());
                    maxDist = (--minNodes.end())->first;
                }
                Qj.push_back(std::make_pair(d,*it2));
            }
        }
        double sep = maxDist + pow(base, level);
        size = Qj.size();
        for(int i=0; i<size; i++) {
            if(Qj[i].first > sep) {
                //quickly removes an element from a vector w/o preserving order.
                Qj[i]=Qj.back();
                Qj.pop_back();
                size--; i--;
            }
        }
    }
    std::vector<CoverTreeNode*> kNN;
    typename std::set<distNodePair>::const_iterator it;
    for(it=minNodes.begin();it!=minNodes.end();++it) {
        kNN.push_back(it->second);
    }
    return kNN;
}
template<class Point>
bool CoverTree<Point>::insert_rec(const Point& p,
                                  const std::vector<distNodePair>& Qi,
                                  const int& level)
{
    std::vector<std::pair<double, CoverTreeNode*> > Qj;
    double sep = pow(base,level);
    double minDist = DBL_MAX;
    std::pair<double,CoverTreeNode*> minQiDist(DBL_MAX,NULL);
    typename  std::vector<std::pair<double, CoverTreeNode*> >::const_iterator it;
    for(it=Qi.begin(); it!=Qi.end(); ++it) {
        if(it->first<minQiDist.first) minQiDist = *it;
        if(it->first<minDist) minDist=it->first;
        if(it->first<=sep) Qj.push_back(*it);
        std::vector<CoverTreeNode*> children = it->second->get_children(level);
        typename std::vector<CoverTreeNode*>::const_iterator it2;
        for(it2=children.begin();it2!=children.end();++it2) {
            double d = p.distance((*it2)->get_point());
            if(d<minDist) minDist = d;
            if(d<=sep) {
                Qj.push_back(std::make_pair(d,*it2));
            }
        }
    }
    //std::cout << "level: " << level << ", sep: " << sep << ", dist: " << minQDist.first << "\n";
    if(minDist > sep) {
        return true;
    } else {
        bool found = insert_rec(p,Qj,level-1);
        //distNodePair minQiDist = distance(p,Qi);
        if(found && minQiDist.first <= sep) {
            if(level-1<_minLevel) _minLevel=level-1;
            minQiDist.second->add_child(level, new CoverTreeNode(p));
            //std::cout << "parent is ";
            //minQiDist.second->get_point().print();
            _numNodes++;
            return false;
        } else {
            return found;
        }
    }
}

template<class Point>
void CoverTree<Point>::remove_rec(const Point& p,
                                  std::map<int,std::vector<distNodePair> >& coverSets,
                                  int level,
                                  bool& multi)
{
    std::vector<distNodePair>& Qi = coverSets[level];
    std::vector<distNodePair>& Qj = coverSets[level-1];
    double minDist = DBL_MAX;
    CoverTreeNode* minNode = _root;
    CoverTreeNode* parent = 0;
    double sep = pow(base, level);
    typename std::vector<distNodePair>::const_iterator it;
    //set Qj to be all children q of Qi such that p.distance(q)<=sep
    //and also keep track of the minimum distance from p to a node in Qj
    //note that every node has itself as a child, but the
    //get_children function only returns non-self-children.
    for(it=Qi.begin();it!=Qi.end();++it) {
        std::vector<CoverTreeNode*> children = it->second->get_children(level);
        double dist = it->first;
        if(dist<minDist) {
            minDist = dist;
            minNode = it->second;
        }
        if(dist <= sep) {
            Qj.push_back(*it);
        }
        typename std::vector<CoverTreeNode*>::const_iterator it2;
        for(it2=children.begin();it2!=children.end();++it2) {
            dist = p.distance((*it2)->get_point());
            if(dist<minDist) {
                minDist = dist;
                minNode = *it2;
                if(dist == 0.0) parent = it->second;
            }
            if(dist <= sep) {
                Qj.push_back(std::make_pair(dist,*it2));
            }
        }
    }
    if(level>_minLevel) remove_rec(p,coverSets,level-1,multi);
    if(minNode->has_point(p)) {
        //the multi flag indicates the point we removed is from a
        //node containing multiple points, and we have removed it,
        //so we don't need to do anything else.
        if(multi) return;
        if(!minNode->is_single()) {
            minNode->remove_point(p);
            multi=true;
            return;
        }
        if(parent!=NULL) parent->remove_child(level, minNode);
        std::vector<CoverTreeNode*> children = minNode->get_children(level-1);
        std::vector<distNodePair>& Q = coverSets[level-1];
        if(Q.size()==1 && Q[0].second==minNode) {
            Q.pop_back();
        } else {
            for(unsigned int i=0;i<Q.size();i++) {
                if(Q[i].second==minNode) {
                    Q[i]=Q.back();
                    Q.pop_back();
                    break;
                }
            }
        }
        typename std::vector<CoverTreeNode*>::const_iterator it;
        for(it=children.begin();it!=children.end();++it) {
            int i = level-1;
            Point q = (*it)->get_point();
            double minDQ = DBL_MAX;
            CoverTreeNode* minDQNode;
            double sep = pow(base,i);
            bool br=false;
            while(true) {
                std::vector<distNodePair>&
                    Q = coverSets[i];
                typename std::vector<distNodePair>::const_iterator it2;
                minDQ = DBL_MAX;
                for(it2=Q.begin();it2!=Q.end();++it2) {
                    double d = q.distance(it2->second->get_point());
                    if(d<minDQ) {
                        minDQ = d;
                        minDQNode = it2->second;
                        if(d <=sep) {
                            br=true;
                            break;
                        }
                    }
                }
                minDQ=DBL_MAX;
                if(br) break;
                Q.push_back(std::make_pair((*it)->distance(p),*it));
                i++;
                sep = pow(base,i);
            }
            //minDQNode->get_point().print();
            //std::cout << " is level " << i << " parent of ";
            //(*it)->get_point().print();
            minDQNode->add_child(i,*it);
        }
        if(parent!=NULL) {
            delete minNode;
            _numNodes--;
        }
    }
}

template<class Point>
std::pair<double, typename CoverTree<Point>::CoverTreeNode*>
CoverTree<Point>::distance(const Point& p,
                           const std::vector<CoverTreeNode*>& Q)
{
    double minDist = DBL_MAX;
    CoverTreeNode* minNode;
    typename std::vector<CoverTreeNode*>::const_iterator it;
    for(it=Q.begin();it!=Q.end();++it) {
        double dist = p.distance((*it)->get_point());
        if(dist < minDist) {
            minDist = dist;
            minNode = *it;
        }
    }
    return std::make_pair(minDist,minNode);
}

template<class Point>
void CoverTree<Point>::insert(const Point& newPoint)
{
    if(_root==NULL) {
        _root = new CoverTreeNode(newPoint);
        _numNodes=1;
        return;
    }
    //TODO: this is pretty inefficient, there may be a better way
    //to check if the node already exists...
    CoverTreeNode* n = k_nearest_nodes(newPoint,1)[0];
    if(newPoint.distance(n->get_point())==0.0) {
        n->add_point(newPoint);
    } else {
        //insert_rec acts under the assumption that there are no nodes with
        //distance 0 to newPoint in the cover tree (the previous lines check it)
        insert_rec(newPoint,
                   std::vector<distNodePair>
                   (1,std::make_pair(_root->distance(newPoint),_root)),
                   _maxLevel);
    }
}

template<class Point>
void CoverTree<Point>::remove(const Point& p)
{
    //Most of this function's code is for the special case of removing the root
    if(_root==NULL) return;
    bool removingRoot=_root->has_point(p);
    if(removingRoot && !_root->is_single()) {
        _root->remove_point(p);
        return;
    }
    CoverTreeNode* newRoot=NULL;
    if(removingRoot) {
        if(_numNodes==1) {
            //removing the last node...
            delete _root;
            _numNodes--;
            _root=NULL;
            return;
        } else {
            for(int i=_maxLevel;i>_minLevel;i--) {
                if(!(_root->get_children(i).empty())) {
                    newRoot = _root->get_children(i).back();
                    _root->remove_child(i, newRoot);
                    break;
                }
            }
        }
    }
    std::map<int, std::vector<distNodePair> > coverSets;
    coverSets[_maxLevel].push_back(std::make_pair(_root->distance(p),_root));
    if(removingRoot)
        coverSets[_maxLevel].push_back(std::make_pair(newRoot->distance(p),newRoot));
    bool multi = false;
    remove_rec(p,coverSets,_maxLevel,multi);
    if(removingRoot) {
        delete _root;
        _numNodes--;
        _root=newRoot;
    }
}

template<class Point>
std::vector<Point> CoverTree<Point>::k_nearest_neighbors(const Point& p,
                                                         const unsigned int& k) const
{
    if(_root==NULL) return std::vector<Point>();
    std::vector<CoverTreeNode*> v = k_nearest_nodes(p, k);
    std::vector<Point> kNN;
    typename std::vector<CoverTreeNode*>::const_iterator it;
    for(it=v.begin();it!=v.end();++it) {
        const std::vector<Point>& p = (*it)->get_points();
        kNN.insert(kNN.end(),p.begin(),p.end());
        if(kNN.size() >= k) break;
    }
    return kNN;
}

template<class Point>
void CoverTree<Point>::print() const
{
    int d = _maxLevel-_minLevel+1;
    std::vector<CoverTreeNode*> Q;
    Q.push_back(_root);
    for(int i=0;i<d;i++) {
        std::cout << "LEVEL " << _maxLevel-i << "\n";
        typename std::vector<CoverTreeNode*>::const_iterator it;
        for(it=Q.begin();it!=Q.end();++it) {
            (*it)->get_point().print();
            std::vector<CoverTreeNode*>
                children = (*it)->get_children(_maxLevel-i);
            typename std::vector<CoverTreeNode*>::const_iterator it2;
            for(it2=children.begin();it2!=children.end();++it2) {
                std::cout << "  ";
                (*it2)->get_point().print();
            }
        }
        std::vector<CoverTreeNode*> newQ;
        for(it=Q.begin();it!=Q.end();++it) {
            std::vector<CoverTreeNode*>
                children = (*it)->get_children(_maxLevel-i);
            newQ.insert(newQ.end(),children.begin(),children.end());
        }
        Q.insert(Q.end(),newQ.begin(),newQ.end());
        std::cout << "\n\n";
    }
}

template<class Point>
typename CoverTree<Point>::CoverTreeNode* CoverTree<Point>::get_root() const
{
    return _root;
}

template<class Point>
CoverTree<Point>::CoverTreeNode::CoverTreeNode(const Point& p) {
    _points.push_back(p);
}

template<class Point>
std::vector<typename CoverTree<Point>::CoverTreeNode*>
CoverTree<Point>::CoverTreeNode::get_children(int level) const
{
    typename std::map<int,std::vector<CoverTreeNode*> >::const_iterator
        it = _childMap.find(level);
    if(it!=_childMap.end()) {
        return it->second;
    }
    return std::vector<CoverTreeNode*>();
}

template<class Point>
void CoverTree<Point>::CoverTreeNode::add_child(int level, CoverTreeNode* p)
{
    _childMap[level].push_back(p);
}

template<class Point>
void CoverTree<Point>::CoverTreeNode::remove_child(int level, CoverTreeNode* p)
{
    std::vector<CoverTreeNode*>& v = _childMap[level];
    for(unsigned int i=0;i<v.size();i++) {
        if(v[i]==p) {
            v[i]=v.back();
            v.pop_back();
            break;
        }
    }
}

template<class Point>
void CoverTree<Point>::CoverTreeNode::add_point(const Point& p)
{
    if(find(_points.begin(), _points.end(), p) == _points.end())
        _points.push_back(p);
}

template<class Point>
void CoverTree<Point>::CoverTreeNode::remove_point(const Point& p)
{
    typename std::vector<Point>::iterator it =
        find(_points.begin(), _points.end(), p);
    if(it != _points.end())
        _points.erase(it);
}

template<class Point>
double CoverTree<Point>::CoverTreeNode::distance(const CoverTreeNode& p) const
{
    return _points[0].distance(p.get_point());
}

template<class Point>
bool CoverTree<Point>::CoverTreeNode::is_single() const
{
    return _points.size() == 1;
}

template<class Point>
bool CoverTree<Point>::CoverTreeNode::has_point(const Point& p) const
{
    return find(_points.begin(), _points.end(), p) != _points.end();
}

template<class Point>
const Point& CoverTree<Point>::CoverTreeNode::get_point() const { return _points[0]; }

template<class Point>
std::vector<typename CoverTree<Point>::CoverTreeNode*>
CoverTree<Point>::CoverTreeNode::get_all_children() const
{
    std::vector<CoverTreeNode*> children;
    typename std::map<int,std::vector<CoverTreeNode*> >::const_iterator it;
    for(it=_childMap.begin();it!=_childMap.end();++it) {
        children.insert(children.end(), it->second.begin(), it->second.end());
    }
    return children;
}

template<class Point>
bool CoverTree<Point>::is_valid_tree() const {
    if(_numNodes==0)
        return _root==NULL;

    std::vector<CoverTreeNode*> nodes;
    nodes.push_back(_root);
    for(int i=_maxLevel;i>_minLevel;i--) {
        double sep = pow(base,i);
        typename std::vector<CoverTreeNode*>::const_iterator it, it2;
        //verify separation invariant of cover tree: for each level,
        //every point is farther than base^level away
        for(it=nodes.begin(); it!=nodes.end(); ++it) {
            for(it2=nodes.begin(); it2!=nodes.end(); ++it2) {
                double dist=(*it)->distance((*it2)->get_point());
                if(dist<=sep && dist!=0.0) {
                    std::cout << "Level " << i << " Separation invariant failed.\n";
                    return false;
                }
            }
        }
        std::vector<CoverTreeNode*> allChildren;
        for(it=nodes.begin(); it!=nodes.end(); ++it) {
            std::vector<CoverTreeNode*> children = (*it)->get_children(i);
            //verify covering tree invariant: the children of node n at level
            //i are no further than base^i away
            for(it2=children.begin(); it2!=children.end(); ++it2) {
                double dist = (*it2)->distance((*it)->get_point());
                if(dist>sep) {
                    std::cout << "Level" << i << " covering tree invariant failed.n";
                    return false;
                }
            }
            allChildren.insert
                (allChildren.end(),children.begin(),children.end());
        }
        nodes.insert(nodes.begin(),allChildren.begin(),allChildren.end());
    }
    return true;
}

/** @}*/
#endif // _COVER_TREE_H

