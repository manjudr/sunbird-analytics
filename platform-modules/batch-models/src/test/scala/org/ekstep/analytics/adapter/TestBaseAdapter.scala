package org.ekstep.analytics.adapter

import org.ekstep.analytics.model.BaseSpec
import org.ekstep.analytics.framework.Response
import org.ekstep.analytics.framework.exception.DataAdapterException
import org.ekstep.analytics.framework.DomainResponse
import org.scalatest.Ignore

/**
 * @author Santhosh
 */

@Ignore @deprecated
object SampleBaseAdapter extends BaseAdapter {
    
}

@Ignore @deprecated
class TestBaseAdapter extends BaseSpec {
    
    "BaseAdapter" should "test all the methods" in {
        
        val resp = Response(null, null, null, null, "ERROR", null)
        a[DataAdapterException] should be thrownBy {
            SampleBaseAdapter.checkResponse(resp)
        }
        
        val domainResp = DomainResponse(null, null, null, null, "ERROR", null)
        a[DataAdapterException] should be thrownBy {
            SampleBaseAdapter.checkResponse(domainResp)
        }
        
        val contentResp = ContentResponse(null, null, null, null, "ERROR", null)
        a[DataAdapterException] should be thrownBy {
            SampleBaseAdapter.checkResponse(contentResp)
        }
        
    }
  
}