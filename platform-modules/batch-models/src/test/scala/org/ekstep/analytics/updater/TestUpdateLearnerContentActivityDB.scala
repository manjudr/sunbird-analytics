package org.ekstep.analytics.updater

import org.ekstep.analytics.model.SparkSpec
import org.ekstep.analytics.framework.MeasuredEvent
import org.ekstep.analytics.framework.DerivedEvent
import com.datastax.spark.connector._
import java.util.UUID

import org.scalatest.Ignore

@Ignore @deprecated
class TestUpdateLearnerContentActivityDB extends SparkSpec(null) {

    "UpdateLearnerContentActivityDB" should " write activities into learneractivity table and check the fields" in {

        val learnerContentAct = LearnerContentActivity("test-user-123", "test_content", 0.0d, 1, 1);
        val rdd = sc.parallelize(Array(learnerContentAct));
        rdd.saveToCassandra("local_learner_db", "learnercontentsummary");

        val rdd1 = loadFile[DerivedEvent]("src/test/resources/learner-content-summary/learner_content_test_sample.log");
        UpdateLearnerContentActivityDB.execute(rdd1, Option(Map("modelVersion" -> "1.0", "modelId" -> "LearnerContentActivitySummary")));
        val rowRDD = sc.cassandraTable[LearnerContentActivity]("local_learner_db", "learnercontentsummary");
        val learnerContent = rowRDD.map { x => ((x.learner_id), (x.content_id, x.interactions_per_min, x.num_of_sessions_played, x.time_spent)) }.collect.toMap;

        val user1 = learnerContent.get("test-user-123").get;

        user1._2 should be(1)
        user1._3 should be(1)
        user1._4 should be(0)
        
    }
    
}