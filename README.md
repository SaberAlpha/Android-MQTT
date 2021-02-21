# Android-MQTT
Android MQTT客户端集成
1.采用kotlin进行基础代码编写
#### 添加依赖
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

	dependencies {
	       implementation 'com.github.SaberAlpha:Android-MQTT:1.0.2'
	}
	
#### MQTT服务器搭建测试	
1.利用Apache-apollo创建服务器实例
2.利用mqttfx调试MQTT的客户端
3.利用mqttfx进行消息的发布,订阅

### 相关参考资料
* [Android消息推送MQTT实战](https://www.jianshu.com/p/73436a5cf855)
* [mqttfx](http://www.jensd.de/apps/mqttfx/)
* [官方Android项目地址](https://github.com/eclipse/paho.mqtt.android)
* [什么是MQTT](https://www.ibm.com/developerworks/cn/iot/iot-mqtt-why-good-for-iot/index.html)
* [一文读懂MQTT协议](https://blog.csdn.net/aa1215018028/article/details/84888096)
* [MQTT比TCP协议好在哪儿？](https://www.zhihu.com/question/23373904)
