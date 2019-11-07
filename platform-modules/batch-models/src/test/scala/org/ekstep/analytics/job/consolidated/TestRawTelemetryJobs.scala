package org.ekstep.analytics.job.consolidated

import org.ekstep.analytics.model.SparkSpec
import org.ekstep.analytics.framework.JobConfig
import org.ekstep.analytics.framework.Fetcher
import org.ekstep.analytics.framework.Query
import org.ekstep.analytics.framework.Dispatcher
import org.ekstep.analytics.framework.util.JSONUtils


class TestRawTelemetryJobs extends SparkSpec(null) {
     
    "RawTelemetryJobs" should "execute all raw telemetry jobs" in {
        val config = JobConfig(Fetcher("local", None, Option(Array(Query(None, None, None, None, None, None, None, None, None, Option("src/test/resources/session-summary/test_data1.log"))))), null, null, "org.ekstep.analytics.model.LearnerSessionSummary", None, Option(Array(Dispatcher("console", Map("printEvent" -> false.asInstanceOf[AnyRef])))), Option(10), Option("TestLearnerSessionSummarizer"), Option(true))
        RawTelemetryJobs.main(JSONUtils.serialize(config))(Option(sc));
    } 
}