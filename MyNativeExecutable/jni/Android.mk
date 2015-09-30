LOCAL_PATH:= $(call my-dir)

include $(CLEAR_VARS)

LOCAL_CPP_EXTENSION := .cpp .cc

LOCAL_SRC_FILES = hello.cpp

LOCAL_MODULE := hello

include $(BUILD_EXECUTABLE)
