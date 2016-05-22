package graph

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import it.nerdammer.spark.hbase._
import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.client.ConnectionFactory
import org.apache.hadoop.hbase.TableName
import org.apache.hadoop.hbase.HTableDescriptor
import org.apache.hadoop.hbase.HColumnDescriptor
import org.apache.spark.graphx._
import org.apache.hadoop.hbase.client.{ HBaseAdmin, HTable, Put, Get }
import org.apache.hadoop.hbase.util.Bytes

/**
 * @author liyingtao
 */
object Connector {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf
    conf.setMaster("local").setAppName("hbase-connector")
    conf.set("spark.hbase.host", "172.17.0.2")

    val sc = new SparkContext(conf)
    print("init env finished \n")

    //    anouther way
    val hbaseconf = HBaseConfiguration.create()
    hbaseconf.set("hbase.zookeeper.property.clientPort", "2181")
    hbaseconf.set("hbase.zookeeper.quorum", "172.17.0.2")
    hbaseconf.set("hbase.master", "172.17.0.2:60000");
//
//    val conn = ConnectionFactory.createConnection(hbaseconf)
//
//    val table = TableName.valueOf("test_table")
//    val tableDesc = new HTableDescriptor(table)
//    tableDesc.addFamily(new HColumnDescriptor("f".getBytes))
//    print("creating...\n")
//
//    val admin = conn.getAdmin
//
//    if (admin.tableExists(table)) {
//      admin.disableTable(table)
//      admin.deleteTable(table)
//    }
//    admin.createTable(tableDesc)
//    print("Created!\n")

    // a way
//    val rdd = sc.parallelize(1 to 100).map(i => (i.toString(), i + 1, "hello"))
//
//    print("start to insert data...\n")
//    rdd.toHBaseTable("test_table").toColumns("column1", "column3").inColumnFamily("f").save()
//
//    print("Insert data finished!\n")
    
    print("start query data...\n")
    //    val table = TableName.valueOf("test_table")

    val theget = new Get(Bytes.toBytes("10001"))
    val result = (new HTable(hbaseconf, "test_table")).get(theget)
//    val value = result.value()
    print("queried value:"+result.toString()+"\n")

    print("query finished.\n")

  }
}