package hello

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
//import sqlContext.implicits._
import it.nerdammer.spark.hbase._

/**
 * @author yingtao.lyt
 */

object TestConn {
  def main(args: Array[String]) {
    val conf = new SparkConf
    //    conf.setMaster("spark://101.69.176.87:7077")
    //    conf.setMaster("spark://192.168.0.114:7077")
    conf.setMaster("local")
    conf.set("spark.app.name", "hbase-connector")

    conf.set("spark.hbase.host", "192.168.99.100")
    //    val sc = new SparkContext("spark://192.168.0.114", "first-tryspark")
    val sc = new SparkContext(conf)
    print("Server start time:" + sc.startTime)
    //    val rawblocks = sc.textFile("file:a.json")
    //    println(rawblocks.first)
    //    val sqlContext = new org.apache.spark.sql.SQLContext(sc)
    //    val df = sqlContext.read.json("./data/a.json")
    //    df.show();

    val rdd = sc.parallelize(100 to 200)
      .map(i => (i.toString, i + 1, "Hello", "append"))
    rdd.toHBaseTable("mytable").toColumns("column1", "column2", "column3").inColumnFamily("mycf").save()

    print("Execute end!")
  }
}

