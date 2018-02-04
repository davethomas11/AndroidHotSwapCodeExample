package com.example.dynamicmodule

import android.util.Log
import dalvik.system.DexClassLoader
import java.io.File

class ModuleLoader(val cacheDir: String) {
    
    fun load(dex: File, cls: String = "com.example.dynamicmodule.DynamicModule"): IDynamicModule {

        try {
            val classLoader = DexClassLoader(dex.absolutePath, cacheDir,
                    null, this.javaClass.classLoader)

            val moduleClass = classLoader.loadClass(cls)
            if (IDynamicModule::class.java.isAssignableFrom(moduleClass)) {
                return moduleClass.newInstance() as IDynamicModule
            }
        } catch (e: Exception) {
            Log.e("ModuleLoader", e.message, e)
        }

        return IDynamicModule { "Failed to load" }
    }    
}