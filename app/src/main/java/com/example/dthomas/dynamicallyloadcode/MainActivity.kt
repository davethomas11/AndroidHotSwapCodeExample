package com.example.dthomas.dynamicallyloadcode

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.dynamicmodule.ModuleLoader
import kotlinx.android.synthetic.main.activity_main.*
import org.apache.commons.io.FileUtils
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text_view.text = loadDexFile("dm.dex").text
        textView2.text = loadDexFile("dm2.dex").text
    }

    fun sourceFile(name: String) = packageManager.getPackageInfo(packageName, 0)
            .applicationInfo.dataDir + "/files/" + name

    fun copyDexFile(name: String) = FileUtils.copyToFile(assets.open(name), File(sourceFile(name)))
            .let { File(sourceFile(name)) }

    fun loadDexFile(name: String) = ModuleLoader().load(copyDexFile(name))
}
