#ifndef __SPW_H__
#define __SPW_H__

#include <memory>

namespace cogroid {

template <typename T> class SPW;

template <typename T>
class SPW {
	std::shared_ptr<T> _object;

public:
	template <typename ...ARGS>
	explicit SPW(ARGS... a) {
		_object = std::make_shared<T>(a...);
	}

	explicit SPW(std::shared_ptr<T> obj) {
		_object = obj;
	}

	virtual ~SPW() noexcept = default;

	long instance() {
		return reinterpret_cast<long>(this);
	}

	std::shared_ptr<T> object() {
		return _object;
	}

	T *get() {
		return _object.get();
	}

	static SPW<T> *get(long jni_ptr) {
		return reinterpret_cast<SPW<T> *>(jni_ptr);
	}

	static std::shared_ptr<T> object(long jni_ptr) {
		return get(jni_ptr)->get();
	}

	static long dispose(long jni_ptr) {
		auto obj = get(jni_ptr);
		delete obj;
		return 0;
	}
};

}

#endif
