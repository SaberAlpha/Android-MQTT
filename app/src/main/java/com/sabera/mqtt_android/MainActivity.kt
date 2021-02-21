package com.sabera.mqtt_android

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.sabera.mqtt_lib.MqttConfig
import com.sabera.mqtt_lib.MqttManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var subscriptionTopic = "topic_test"
    private var publishTopic = "AndroidPublishTopic"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initMQtt()
    }

    private fun initMQtt(){
        // 初始化
        MqttManager.getInstance().init(
            this,
            MqttConfig().create()
        )

        showTips("服务器地址：${MqttManager.getInstance().getServerUrl()}")
    }

    // 连接服务端
    fun btnConnect(view: View) {
        showTips("正在连接中...")
        MqttManager.getInstance().connect {
            onConnectSuccess {
                showTips("服务器连接成功")
            }
            onConnectFailed {
                showTips("服务器连接失败：${it?.message}")
            }
        }
    }

    // 订阅主题
    @SuppressLint("SetTextI18n")
    fun btnSubscribe(view: View) {
        showTips("正在订阅中...")
        val topic = et_subscribe.text.toString()
        if (!TextUtils.isEmpty(topic)) subscriptionTopic = topic
        MqttManager.getInstance().subscribe(subscriptionTopic) {

            onSubscriberSuccess {
                showTips("订阅成功")
            }

            onSubscriberFailed {
                showTips("订阅失败：${it?.message}")
            }

            onMessageArrived { topic, message, qos ->
                tv_msg.text = "订阅消息：$message"
            }

            onDeliveryComplete {
                showTips("消息推送完毕：$it")
            }

            onConnectionLost {
                showTips("连接已断开")
            }
        }
    }

    // 推送消息
    fun btnPublish(view: View) {
        showTips("正在推送中...")
        val topic = et_publish.text.toString()
        if (!TextUtils.isEmpty(topic)) publishTopic = topic
        MqttManager.getInstance().publishMessage(publishTopic, et_pub.text.toString())
    }

    // 断开连接
    fun btnClose(view: View) {
        showTips("正在断开中...")
        MqttManager.getInstance().disconnect()
    }

    private fun showTips(msg: String?) {
        tvMessage?.text = msg
    }


    override fun onDestroy() {
        super.onDestroy()
        MqttManager.getInstance().close()
    }
}
