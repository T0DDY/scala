package hello

import org.apache.spark.{ SparkContext, SparkConf }

object Helloworld {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("SimpleGraphX").setMaster("local")
    val sc = new SparkContext(conf)

    val textFile = sc.textFile("src/com/wyunbank/scala/data/BasicInfoItem_2016-05-17.json")
    val countCunxu = textFile.filter { line => line.contains("存续")}.count()
    print("cunxu :"+countCunxu)
  }
}

