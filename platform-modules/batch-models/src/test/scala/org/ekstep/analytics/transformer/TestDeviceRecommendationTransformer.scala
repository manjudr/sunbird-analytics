package org.ekstep.analytics.transformer

import org.ekstep.analytics.model.SparkSpec
import org.scalatest.Ignore

@Ignore @deprecated
class TestDeviceRecommendationTransformer extends SparkSpec {

    "DeviceRecommendationTransformer" should "perform outlier" in {

        ContentUsageTransformer.name() should be("DeviceRecommendationTransformer")
    }
    
}