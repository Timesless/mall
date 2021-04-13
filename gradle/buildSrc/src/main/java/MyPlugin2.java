

import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * @author yangzl
 * @date 2021/2/8
 * @desc
 *
 * 		gradle 插件抽取到 Java类
 * 		该类不需要声明packeage
 *
 * 	gradle整个过程是如何实现的呢？
 * 	该过程类似于maven的install 本地仓库
 * 		1. 通过约定在buildSrc
 * 		2. 在外层build.gradle运行之前先编译buildSrc
 * 		3. 将buildSrc打包的结果libs下，放入buildScript 下 dependencies { classpath 中 }，见build.gradle
 *
 */
public class MyPlugin2 implements Plugin<Project> {

	@Override
	public void apply(Project project) {
		for (int i = 10; i < 15; i++) {
			System.out.println("**** java plugin" + i + " configure ****");
			project.task("task" + i);
		}
	}
}
