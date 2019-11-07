package org.ekstep.analytics.job.summarizer

import org.ekstep.analytics.model.SparkSpec
import org.ekstep.analytics.framework.JobConfig
import org.ekstep.analytics.framework.Fetcher
import org.ekstep.analytics.framework.Query
import org.ekstep.analytics.framework.util.JSONUtils
import org.ekstep.analytics.framework.Dispatcher
import org.scalatest.Ignore

@Ignore @deprecated
class TestContentUsageSummarizer extends SparkSpec(null) {
    
    "ContentUsageSummarizer" should "execute the job and shouldn't throw any exception" in {
        val config = JobConfig(Fetcher("local", None, Option(Array(Query(None, None, None, None, None, None, None, None, None, Option("src/test/resources/content-usage-summary-model/content_summary_test_data.log"))))), None, None, "org.ekstep.analytics.model.ContentUsageSummaryModel", None, Option(Array(Dispatcher("console", Map("printEvent" -> false.asInstanceOf[AnyRef])))), Option(10), Option("TestContentUsageSummarizer"), Option(false))
        ContentUsageSummarizer.main(JSONUtils.serialize(config))(Option(sc));
    }
  
}