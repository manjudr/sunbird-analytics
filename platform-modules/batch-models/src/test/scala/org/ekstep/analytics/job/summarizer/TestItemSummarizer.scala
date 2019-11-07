package org.ekstep.analytics.job.summarizer

import org.ekstep.analytics.framework.JobConfig
import org.ekstep.analytics.framework.Fetcher
import org.ekstep.analytics.framework.Query
import org.ekstep.analytics.framework.Dispatcher
import org.ekstep.analytics.model.SparkSpec
import org.ekstep.analytics.framework.util.JSONUtils
import org.scalatest.Ignore

@Ignore @deprecated
class TestItemSummarizer extends SparkSpec(null) {
  
    "ItemSummarizer" should "execute the job and won't throw any Exception" in {
        val config = JobConfig(Fetcher("local", None, Option(Array(Query(None, None, None, None, None, None, None, None, None, Option("src/test/resources/item-summary-model/item_summary_1.log"))))), null, null, "org.ekstep.analytics.model.ItemSummaryModel", None, Option(Array(Dispatcher("console", Map("printEvent" -> false.asInstanceOf[AnyRef])))), Option(10), Option("TestItemSummarizer"), Option(true))
        ItemSummarizer.main(JSONUtils.serialize(config))(Option(sc));
    }
    
}