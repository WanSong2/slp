package com.songw.slp.restController;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RestControllerTest {

	@Test
	public void shouldInvert() {
		Inverter inverted1 = new Inverter();
		String inverted = inverted1.invert("a");

		// 예상값과 실제 값이 같은지 확인 
        assertEquals(inverted, "a");
		assertEquals(inverted, "");
		assertEquals(inverted, null);
		assertEquals(inverted, "abcd");
		
        
        //Assert.fail();
	}
	
	private class Inverter {
		public String invert(String s) {
			// 문자열이 null 이거나 비어 있는 경우 빈 문자열 반환
			if (s == null || s.isEmpty()) {
				return "";
			// 문자열이 1자 보다 길면 반전된 버전이 반환
			} else if (s.length() > 1) {
				return reverse(s);
			}
			
			// 그렇지 않고 1자 인 경우에는 그대로 출 
			return s;
		}
		
		public String reverse(String s) {
			String reverse = "";
			for (int i=s.length()-1; i>=0; i--) {
				reverse = reverse + s.charAt(i);
			}
			return reverse;
		}
	}
}

