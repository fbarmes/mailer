package fr.tricotain.mailer.service.utils;

import org.junit.Test;

import junit.framework.Assert;

public class StringUtilsTest {

	@Test
	public void testIsNullOrEmpty() throws Exception {
		
		Assert.assertTrue(StringUtils.isNullOrEmpty(null));
		Assert.assertTrue(StringUtils.isNullOrEmpty(""));
		Assert.assertFalse(StringUtils.isNullOrEmpty(" "));
		Assert.assertFalse(StringUtils.isNullOrEmpty("as"));
		
	}
	
}
