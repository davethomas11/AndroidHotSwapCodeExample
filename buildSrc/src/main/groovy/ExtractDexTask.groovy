import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.process.internal.ExecAction
import org.gradle.process.internal.ExecActionFactory

import javax.inject.Inject

class ExtractDexTask extends DefaultTask {

    String androidSDKLocation
    String buildToolsVersion
    String srcFolder = "build/outputs/aar/"
    String srcName
    String srcVariant = "debug"
    String srcPkgType = "aar"
    String destinationFolder = "../app/src/main/assets"
    String dexName

    @TaskAction
    def extractDex() {
        unzipPkg()
        dexClassFile()
    }

    def unzipPkg() {
        getProject().copy {
            from getProject().zipTree("${srcFolder}/${srcName}-${srcVariant}.${srcPkgType}")
            into "${srcFolder}/${srcName}"
        }
    }

    def dexClassFile() {
        ExecAction execAction = getExecActionFactory().newExecAction()
        execAction.setExecutable("${androidSDKLocation}/build-tools/${buildToolsVersion}/dx")
        execAction.setArgs(["--dex", "--output",
                "${destinationFolder}/${dexName}.dex",
                "${srcFolder}/${srcName}/classes.jar"])
        execAction.execute()
    }

    @Inject
    protected ExecActionFactory getExecActionFactory() {
        throw new UnsupportedOperationException()
    }
}