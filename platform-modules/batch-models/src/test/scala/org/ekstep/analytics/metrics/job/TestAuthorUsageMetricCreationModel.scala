package org.ekstep.analytics.metrics.job

import org.ekstep.analytics.framework.util.CommonUtil
import org.ekstep.analytics.framework.util.JSONUtils
import org.ekstep.analytics.util.SessionBatchModel
import org.apache.spark.rdd.RDD
import org.apache.spark.SparkContext
import org.ekstep.analytics.framework.DerivedEvent
import org.ekstep.analytics.model.SparkSpec
import org.joda.time.DateTime
import com.datastax.spark.connector.cql.CassandraConnector
import org.ekstep.analytics.updater.UpdateAuthorSummaryDB
import org.scalatest.Ignore

@Ignore @deprecated
class TestAuthorUsageMetricCreationModel extends SparkSpec(null) {
  
    "AuthorUsageMetricCreationModel" should "execute AuthorUsageMetricCreationModel successfully" in {

        CassandraConnector(sc.getConf).withSessionDo { session =>
            session.execute("TRUNCATE local_creation_metrics_db.author_usage_summary_fact");
        }
        
        val start_date = DateTime.now().toString(CommonUtil.dateFormat)
        val input = loadFile[DerivedEvent]("src/test/resources/author-usage-updater/test.log");
        UpdateAuthorSummaryDB.execute(input, Option(Map()))

        val data = sc.parallelize(List(""))
        val rdd2 = AuthorUsageMetricCreationModel.execute(data, Option(Map("start_date" -> start_date.asInstanceOf[AnyRef], "end_date" -> start_date.asInstanceOf[AnyRef])));
        
        rdd2.count() should be(2)
    }
}