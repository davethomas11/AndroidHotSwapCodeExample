package com.example.dynamicmodule

import dalvik.system.DexClassLoader
import java.io.File

class ModuleLoader {
    
    fun load(dex: File): IDynamicModule {
        val classLoader = DexClassLoader(dex.absolutePath, null,
                null, this.javaClass.classLoader)
        val moduleClass = classLoader.loadClass("com.example.dynamicmodule.DynamicModule")

        if (IDynamicModule::class.java.isAssignableFrom(moduleClass)) {
            return moduleClass.newInstance() as IDynamicModule
        }

        return IDynamicModule { "Failed to load" }
    }    
}