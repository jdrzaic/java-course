package hr.fer.zemris.java.webserver.requestcontext.test;

import hr.fer.zemris.java.webserver.RequestContext;
import hr.fer.zemris.java.webserver.RequestContext.RCCookie;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 * Testovi razreda RequestContext i svih njegovih metoda,
 * te podrazreda RCookie.
 * @author jelena
 *
 */
public class TestRequest {

	/**
	 * Metoda testira konstruktor razreda RequestContext
	 * @throws IOException kod problema s ispisom
	 */
	@Test
	public void constructorTest() throws IOException {
		RequestContext rc = new RequestContext(System.out, new HashMap<String, String>(),
				new HashMap<String, String>(),
				new ArrayList<RequestContext.RCCookie>());
		Assert.assertEquals("HTTP/1.1 200 OK\r\n"
				+ "Content-Type:text/html; charset=UTF-8\r\n\r\n", rc.createHeader());
	}
	
	/**
	 * Metoda testira metodu write()
	 * @throws IOException kod problema s outputom
	 */
	@Test
	public void writeTest() throws IOException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		RequestContext rc = new RequestContext(os, new HashMap<String, String>(),
				new HashMap<String, String>(),
				new ArrayList<RequestContext.RCCookie>());
		rc.write("data");
		Assert.assertEquals("HTTP/1.1 200 OK\r\n" + 
				"Content-Type:text/html; charset=UTF-8\r\n\r\n" + "data", 
				os.toString());
		os.reset();
		//vec imamo header
		rc.write("novo");
		Assert.assertEquals("novo", os.toString());
	}
	
	/**
	 * Metoda testira ponasanje implementacije kada je kao stream proslijeden
	 * null.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void nullStreamTest() {
		@SuppressWarnings("unused")
		RequestContext rc = new RequestContext(null, new HashMap<String, String>(),
				new HashMap<String, String>(),
				new ArrayList<RequestContext.RCCookie>());

	}
	
	/**
	 * Metoda testira rad gettera i settera RequestContext 
	 * razreda.
	 */
	@Test
	public void getterSetterTest1() {
		ByteArrayOutputStream os = new ByteArrayOutputStream();

		RequestContext rc = new RequestContext(os, null,
				null,
				new ArrayList<RequestContext.RCCookie>());
		rc.setPersistentParameter("name", "ana");
		rc.setTemporaryParameter("age", "25");
		Assert.assertEquals("ana", rc.getPersistentParameter("name"));
		Assert.assertEquals("25", rc.getTemporaryParameter("age"));
	}
	
	/**
	 * Metoda testira dodavanje cookie-a u 
	 * instancu RequestContext
	 * @throws IOException
	 */
	@Test
	public void addCookieTest() throws IOException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();

		RequestContext rc = new RequestContext(os, null,
				null,
				new ArrayList<RequestContext.RCCookie>());
		rc.addRCCookie(new RCCookie("sid", "ssssss", null, "localhost", "/"));
		rc.write("");
		Assert.assertEquals("HTTP/1.1 200 OK\r\n" + 
				"Content-Type:text/html; charset=UTF-8\r\n" + 
				"Set-Cookie: sid=\"ssssss\"; Domain=localhost; Path=/\r\n\r\n"
				, os.toString());
	}
	
	/**
	 * Metoda testira metodu setEncoding() u razredu 
	 * RequestContext.
	 * @throws IOException kod problema s ispisom
	 */
	@Test
	public void setEncodingTest() throws IOException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();

		RequestContext rc = new RequestContext(os, null,
				null,
				new ArrayList<RequestContext.RCCookie>());
		rc.addRCCookie(new RCCookie("sid", "ssssss", null, "localhost", "/"));
		rc.setEncoding("UTF-16");
		rc.write("");
		Assert.assertEquals("HTTP/1.1 200 OK\r\n" + 
				"Content-Type:text/html; charset=UTF-16\r\n" + 
				"Set-Cookie: sid=\"ssssss\"; Domain=localhost; Path=/\r\n\r\n"
				, os.toString());
	}
	
	/**
	 * Metoda testira rad metode setStatusCode()
	 * @throws IOException kod problema s ispisom
	 */
	@Test(expected=RuntimeException.class)
	public void setStatusCodeTest() throws IOException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();

		RequestContext rc = new RequestContext(os, null,
				null,
				new ArrayList<RequestContext.RCCookie>());
		rc.setStatusCode(202);
		rc.addRCCookie(new RCCookie("sid", "ssssss", null, "localhost", "/"));
		rc.setEncoding("UTF-16");
		rc.write("");
		rc.setStatusCode(555);	
	}

	/**
	 * Metoda testira gettere razreda RCookie.
	 */
	@Test
	public void CookieGettersTest() {
		RCCookie cookie = new RCCookie("cookie", "apple", 8, "localhost", "/");
		Assert.assertEquals("cookie", cookie.getName());
		Assert.assertEquals("apple", cookie.getValue());
		Assert.assertEquals(new Integer(8), cookie.getMaxAge());
		Assert.assertEquals("localhost", cookie.getDomain());
		Assert.assertEquals("/", cookie.getPath());
		Assert.assertEquals("Set-Cookie: cookie=\"apple\"; Domain=localhost; Path=/; Max-Age=8", 
				cookie.toString());
	}
	
	/**
	 * Metoda testira gettere i settere razreda
	 * RequestContext
	 * @throws IOException kod problema s ispisom
	 */
	@Test
	public void getterSetterTest2() throws IOException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();

		RequestContext rc = new RequestContext(os, null,
				null,
				new ArrayList<RequestContext.RCCookie>());
		rc.setStatusText("GOOD");
		rc.setEncoding("UTF-16");
		rc.setMimeType("text/plain");

		rc.write("");
		Assert.assertEquals("HTTP/1.1 200 GOOD\r\n" + 
				"Content-Type:text/plain; charset=UTF-16\r\n\r\n", os.toString());
	}
	
	/**
	 * Metoda testira postavljanje tipa cookiea.
	 */
	@Test
	public void cookieSetTypeTest() {
		RCCookie cookie = new RCCookie("cookie", "apple", 8, "localhost", "/");
		cookie.setType("HttpOnly");
		Assert.assertEquals("Set-Cookie: cookie=\"apple\"; Domain=localhost; Path=/; Max-Age=8; HttpOnly", 
				cookie.toString());
	}
	
	/**
	 * Metoda testira metodu toString()
	 * razreda RCOokie.
	 */
	@Test
	public void cookieToString() {
		RCCookie cookie = new RCCookie("cookie", "apple", null, null, null);
		Assert.assertEquals("Set-Cookie: cookie=\"apple\"", cookie.toString());
	}
	
	/**
	 * Metoda testira rad s permParameters u razredu
	 * RequestContext
	 */
	@Test
	public void permParametersTest() {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		RequestContext rc = new RequestContext(os, null,
				null,
				new ArrayList<RequestContext.RCCookie>());
		rc.setPersistentParameter("day", "monday");
		Assert.assertEquals("monday", rc.getPersistentParameter("day"));
		
		rc.removePersistentParameter("day");
		Assert.assertEquals("[]", rc.getPersistentParameterNames().toString());
	}
	
	/**
	 * Metoda testira rad s parametrima pohranjenim u 
	 * RequestContext instanci.
	 */
	@Test
	public void paramTest() {
		Map<String, String> param = new HashMap<String, String>();
		param.put("day", "sunday");
		param.put("work", "sleep");
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		RequestContext rc = new RequestContext(os, param,
				null,
				new ArrayList<RequestContext.RCCookie>());
		Assert.assertEquals("sunday", rc.getParameter("day"));
	}
	
	/**
	 * Metoda testira rad s privremenim parametrima pohranjenim u 
	 * RequestContext instanci.
	 */
	@Test
	public void tempParametersTest() {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		RequestContext rc = new RequestContext(os, null,
				null,
				new ArrayList<RequestContext.RCCookie>());
		rc.setTemporaryParameter("day", "monday");
		Assert.assertEquals("monday", rc.getTemporaryParameter("day"));
		
		rc.removeTemporaryParameter("day");
		Assert.assertEquals("[]", rc.getTemporaryParameterNames().toString());
	}
}
