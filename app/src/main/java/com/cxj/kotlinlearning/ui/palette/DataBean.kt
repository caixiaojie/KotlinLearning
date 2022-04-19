package com.cxj.kotlinlearning.ui.palette

import com.cxj.kotlinlearning.ui.palette.DataBean
import com.cxj.kotlinlearning.R
import java.util.*

class DataBean {
    var imageRes: Int = 0
    var imageUrl: String? = null
    var title: String?
    var viewType: Int

    constructor(imageRes: Int, title: String?, viewType: Int) {
        this.imageRes = imageRes
        this.title = title
        this.viewType = viewType
    }

    constructor(imageUrl: String?, title: String?, viewType: Int) {
        this.imageUrl = imageUrl
        this.title = title
        this.viewType = viewType
    }

    companion object {
        val testData: List<DataBean>
            get() {
                val list: MutableList<DataBean> = ArrayList()
                list.add(DataBean(R.drawable.image1, "相信自己,你努力的样子真的很美", 1))
                list.add(DataBean(R.drawable.image2, "极致简约,梦幻小屋", 3))
                list.add(DataBean(R.drawable.image3, "超级卖梦人", 3))
                list.add(DataBean(R.drawable.image4, "夏季新搭配", 1))
                list.add(DataBean(R.drawable.image5, "绝美风格搭配", 1))
                list.add(DataBean(R.drawable.image6, "微微一笑 很倾城", 3))
                return list
            }
        val testData2: List<DataBean>
            get() {
                val list: MutableList<DataBean> = ArrayList()
                list.add(DataBean(R.drawable.image7, "听风.赏雨", 3))
                list.add(DataBean(R.drawable.image8, "迪丽热巴.迪力木拉提", 1))
                list.add(DataBean(R.drawable.image99, "爱美.人间有之", 3))
                list.add(DataBean(R.drawable.image10, "洋洋洋.气质篇", 1))
                list.add(DataBean(R.drawable.image11, "生活的态度", 3))
                return list
            }

        /**
         * 仿淘宝商品详情第一个是视频
         * @return
         */
        val testDataVideo: List<DataBean>
            get() {
                val list: MutableList<DataBean> = ArrayList()
                list.add(
                    DataBean(
                        "http://vfx.mtime.cn/Video/2019/03/09/mp4/190309153658147087.mp4",
                        "第一个放视频",
                        2
                    )
                )
                list.add(DataBean(R.drawable.image7, "听风.赏雨", 1))
                list.add(DataBean(R.drawable.image8, "迪丽热巴.迪力木拉提", 1))
                list.add(DataBean(R.drawable.image9, "爱美.人间有之", 1))
                list.add(DataBean(R.drawable.image10, "洋洋洋.气质篇", 1))
                list.add(DataBean(R.drawable.image11, "生活的态度", 1))
                return list
            }
        val testData3: List<DataBean>
            get() {
                val list: MutableList<DataBean> = ArrayList()
                list.add(
                    DataBean(
                        "https://img.zcool.cn/community/013de756fb63036ac7257948747896.jpg",
                        null,
                        1
                    )
                )
                list.add(
                    DataBean(
                        "https://img.zcool.cn/community/01639a56fb62ff6ac725794891960d.jpg",
                        null,
                        1
                    )
                )
                list.add(
                    DataBean(
                        "https://img.zcool.cn/community/01270156fb62fd6ac72579485aa893.jpg",
                        null,
                        1
                    )
                )
                list.add(
                    DataBean(
                        "https://img.zcool.cn/community/01233056fb62fe32f875a9447400e1.jpg",
                        null,
                        1
                    )
                )
                list.add(
                    DataBean(
                        "https://img.zcool.cn/community/016a2256fb63006ac7257948f83349.jpg",
                        null,
                        1
                    )
                )
                return list
            }

        fun getColors(size: Int): List<String> {
            val list: MutableList<String> = ArrayList()
            for (i in 0 until size) {
                list.add(randColor)
            }
            return list
        }

        /**
         * 获取十六进制的颜色代码.例如  "#5A6677"
         * 分别取R、G、B的随机值，然后加起来即可
         *
         * @return String
         */
        val randColor: String
            get() {
                var R: String
                var G: String
                var B: String
                val random = Random()
                R = Integer.toHexString(random.nextInt(256)).toUpperCase()
                G = Integer.toHexString(random.nextInt(256)).toUpperCase()
                B = Integer.toHexString(random.nextInt(256)).toUpperCase()
                R = if (R.length == 1) "0$R" else R
                G = if (G.length == 1) "0$G" else G
                B = if (B.length == 1) "0$B" else B
                return "#$R$G$B"
            }
    }
}