package org.ekstep.analytics.updater

import java.net.URI

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import org.ekstep.analytics.framework.Period._
import org.ekstep.analytics.framework.conf.AppConf
import org.ekstep.analytics.model.SparkSpec
import com.pygmalios.reactiveinflux._
import org.joda.time.DateTimeUtils
import org.scalatest.Ignore

@Ignore @deprecated
class TestUpdateCreationMetricsDB extends SparkSpec(null) {
    
    ignore should "push data into influxDB" in {
        val rdd = loadFile[CreationMetrics]("src/test/resources/influxDB-updater/concepts.json");
        UpdateCreationMetricsDB.execute(rdd, None);
        implicit val awaitAtMost = 10.seconds
        syncInfluxDb(new URI(AppConf.getConfig("reactiveinflux.url")), AppConf.getConfig("reactiveinflux.database")) { db =>
            val queryResult = db.query("SELECT * FROM concept_metrics")
            queryResult.result.isEmpty should be(false)
        }
    }

    ignore should "check count of coulmns in influxdb table" in {
        val rdd = loadFile[CreationMetrics]("src/test/resources/influxDB-updater/template.json");
        UpdateCreationMetricsDB.execute(rdd, None);
        implicit val awaitAtMost = 10.seconds
        syncInfluxDb(new URI(AppConf.getConfig("reactiveinflux.url")), AppConf.getConfig("reactiveinflux.database")) { db =>
            val queryResult = db.query("SELECT * FROM template_metrics")
            queryResult.result.singleSeries.columns.size should be(5)
        }
    }

    ignore should "validate table name" in {
        val rdd = loadFile[CreationMetrics]("src/test/resources/influxDB-updater/template.json");
        UpdateCreationMetricsDB.execute(rdd, None);
        implicit val awaitAtMost = 10.seconds
        syncInfluxDb(new URI(AppConf.getConfig("reactiveinflux.url")), AppConf.getConfig("reactiveinflux.database")) { db =>
            val queryResult = db.query("SELECT * FROM template_metrics")
            queryResult.result.singleSeries.name should be("template_metrics")
        }
    }

    ignore should "generate first coulmn as time " in {
        val rdd = loadFile[CreationMetrics]("src/test/resources/influxDB-updater/template.json");
        UpdateCreationMetricsDB.execute(rdd, None);
        implicit val awaitAtMost = 10.seconds
        syncInfluxDb(new URI(AppConf.getConfig("reactiveinflux.url")), AppConf.getConfig("reactiveinflux.database")) { db =>
            val queryResult = db.query("SELECT * FROM template_metrics")
            queryResult.result.singleSeries.columns(0) should be("time")
        }
    }
    
    ignore should "validate items for concept_id " in {
        implicit val awaitAtMost = 10.seconds
        syncInfluxDb(new URI(AppConf.getConfig("reactiveinflux.url")), AppConf.getConfig("reactiveinflux.database")) { db =>
            val queryResult = db.query("SELECT items FROM concept_metrics where concept_id = 'id7'")
            queryResult.rows.apply(0).mkString.split(",")(1).trim() should be("22")

        }
    }
}