package org.ekstep.analytics.job.updater

import org.ekstep.analytics.model.SparkSpec
import org.ekstep.analytics.framework.JobConfig
import org.ekstep.analytics.framework.Fetcher
import org.ekstep.analytics.framework.Query
import org.ekstep.analytics.framework.Dispatcher
import org.ekstep.analytics.framework.util.JSONUtils
import org.scalatest.Ignore

/**
 * @author yuva
 */
@Ignore @deprecated
class TestTextbookUsageUpdater extends SparkSpec(null) {
  
    it should "execute the job and shouldn't throw any exception" in {
        val config = JobConfig(Fetcher("local", None, Option(Array(Query(None, None, None, None, None, None, None, None, None, Option("src/test/resources/textbook-session-updater/textbook-usage-summary1.log"))))), None, None, "org.ekstep.analytics.updater.TextbookUsageUpdater", None, Option(Array(Dispatcher("console", Map("printEvent" -> false.asInstanceOf[AnyRef])))), Option(10), Option("Textbook usage updater Test"), Option(false))
        TextbookUsageUpdater.main(JSONUtils.serialize(config))(Option(sc));
    }
}