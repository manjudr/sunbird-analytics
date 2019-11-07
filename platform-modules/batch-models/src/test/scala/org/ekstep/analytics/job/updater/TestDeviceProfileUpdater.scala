package org.ekstep.analytics.job.updater

import org.ekstep.analytics.model.SparkSpec
import org.ekstep.analytics.framework.{Dispatcher, Fetcher, JobConfig, Query}
import org.ekstep.analytics.framework.util.JSONUtils
import org.scalatest.Ignore

@Ignore @deprecated
class TestDeviceProfileUpdater extends SparkSpec(null) {
  
    "DeviceProfileUpdater" should "execute the job and shouldn't throw any exception" in {
        val config = JobConfig(Fetcher("local", None, Option(Array(Query(None, None, None, None, None, None, None, None, None, Option("src/test/resources/device-profile/test-data1.log"))))), None, None, "org.ekstep.analytics.updater.DeviceProfileUpdater", None, Option(Array(Dispatcher("console", Map("printEvent" -> false.asInstanceOf[AnyRef])))), Option(10), Option("TestDeviceProfileUpdater"), Option(false))
        DeviceProfileUpdater.main(JSONUtils.serialize(config))(Option(sc));
    }
}