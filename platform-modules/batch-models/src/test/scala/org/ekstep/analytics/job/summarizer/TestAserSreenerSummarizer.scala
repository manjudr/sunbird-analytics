package org.ekstep.analytics.job.summarizer

import org.ekstep.analytics.framework.JobConfig
import org.ekstep.analytics.framework.Fetcher
import org.ekstep.analytics.framework.Query
import org.ekstep.analytics.framework.Dispatcher
import org.ekstep.analytics.model.SparkSpec
import org.ekstep.analytics.framework.util.JSONUtils
import org.scalatest.Ignore

@Ignore @deprecated
class TestAserScreenSummarizer extends SparkSpec(null) {

    it should "execute AserScreenSummarizer job and won't throw any Exception" in {
        val config = JobConfig(Fetcher("local", None, Option(Array(Query(None, None, None, None, None, None, None, None, None, Option("src/test/resources/sample_telemetry.log"))))), null, None, "org.ekstep.analytics.model.AserScreenSummary", None, Option(Array(Dispatcher("console", Map("printEvent" -> false.asInstanceOf[AnyRef])))), Option(10), Option("TestAserScreenSummarizer"))
        AserScreenSummarizer.main(JSONUtils.serialize(config))(Option(sc));
    }
}