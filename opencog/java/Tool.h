#ifndef __TOOL_H__
#define __TOOL_H__
#include <opencog/atoms/atom_types/types.h>

namespace cogroid {

class Tool;

class Tool {

public:
	static opencog::Type node_type(int type) {
		if (type == 2) return opencog::CONCEPT_NODE;
		return opencog::CONCEPT_NODE;
	}
};

}

#endif
