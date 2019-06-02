package it.unipd.dei.clef.servlet;

import it.unipd.dei.clef.resource.*;
import it.unipd.dei.clef.rest.StatisticRestResource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Manages the REST API for the different REST resources.
 */
public final class RestManagerServlet extends AbstractDatabaseServlet {

	/**
	 * The JSON MIME media type
	 */
	private static final String JSON_MEDIA_TYPE = "application/json";

	/**
	 * The JSON UTF-8 MIME media type
	 */
	private static final String JSON_UTF_8_MEDIA_TYPE = "application/json; charset=utf-8";

	/**
	 * The any MIME media type
	 */
	private static final String ALL_MEDIA_TYPE = "*/*";

	@Override
	protected final void service(final HttpServletRequest req, final HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType(JSON_UTF_8_MEDIA_TYPE);
		final OutputStream out = res.getOutputStream();

		try {
			// if the request method and/or the MIME media type are not allowed, return.
			// Appropriate error message sent by {@code checkMethodMediaType}
			if (!checkMethodMediaType(req, res)) {
				return;
			}

			// if the requested resource was a statistic, delegate its processing and return
			if (processStatistic(req, res)) {
				return;
			}

			// if none of the above process methods succeeds, it means an unknow resource has been requested
			final Message m = new Message("Unknown resource requested.", "E4A6",
										  String.format("Requested resource is %s.", req.getRequestURI()));
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			m.toJSON(out);
		} finally {
			// ensure to always flush and close the output stream
			out.flush();
			out.close();
		}
	}

	/**
	 * Checks that the request method and MIME media type are allowed.
	 */
	private boolean checkMethodMediaType(final HttpServletRequest req, final HttpServletResponse res)
			throws IOException {

		final String method = req.getMethod();
		final String contentType = req.getHeader("Content-Type");
		final String accept = req.getHeader("Accept");
		final OutputStream out = res.getOutputStream();

		Message m = null;

		if(accept == null) {
			m = new Message("Output media type not specified.", "E4A1", "Accept request header missing.");
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			m.toJSON(out);
			return false;
		}

		if(!accept.contains(JSON_MEDIA_TYPE) && !accept.equals(ALL_MEDIA_TYPE)) {
			m = new Message("Unsupported output media type. Resources are represented only in application/json.",
							"E4A2", String.format("Requested representation is %s.", accept));
			res.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
			m.toJSON(out);
			return false;
		}

		switch(method) {
			case "POST":
			case "PUT":
			case "DELETE":
				// nothing to do
				break;

			case "GET":
				if(contentType == null) {
					m = new Message("Input media type not specified.", "E4A3", "Content-Type request header missing.");
					res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					m.toJSON(out);
					return false;
				}

				if(!contentType.contains(JSON_MEDIA_TYPE)) {
					m = new Message("Unsupported input media type. Resources are represented only in application/json.",
									"E4A4", String.format("Submitted representation is %s.", contentType));
					res.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
					m.toJSON(out);
					return false;
				}

				break;
			default:
				m = new Message("Unsupported operation.",
								"E4A5", String.format("Requested operation %s.", method));
				res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
				m.toJSON(out);
				return false;
		}

		return true;
	}


	/**
	 * Checks whether the request if for a Statistic resource and, in case, processes it.
	 */
	private boolean processStatistic(HttpServletRequest req, HttpServletResponse res) throws IOException {

		final String method = req.getMethod();
		final OutputStream out = res.getOutputStream();

		String path = req.getRequestURI();
		Message m = null;

		// the requested resource was not a statistic
		if(path.lastIndexOf("rest/statistic") <= 0) {
			return false;
		}

		try {
			// strip everyhing until after the /statistic
			path = path.substring(path.lastIndexOf("statistic") + 9);

			// the request URI is: /statistic/{id}
			if (path.length() == 0 || path.equals("/")) {
				m = new Message("Wrong format for URI rest request",
								"E4A7", String.format("Requested URI: %s.", req.getRequestURI()));
				res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				m.toJSON(res.getOutputStream());
			} else {
				
				// the request URI is: /statistic/coauthors/{id}
				if (path.contains("coauthors")) {
					path = path.substring(path.lastIndexOf("coauthors") + 9);
					
					if (path.length() == 0 || path.equals("/")) {
						m = new Message("Wrong format for URI /statistic/coauthors/{id}: no {id} specified.",
										"E4A7", String.format("Requesed URI: %s.", req.getRequestURI()));
						res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
						m.toJSON(res.getOutputStream());
					} else {
						switch (method) {
							case "GET":
							
								// check that the parameter is actually an integer
								try {
									Integer.parseInt(path.substring(1));
									
									// GET INFORMATION FROM THE DATABASE PIE (COAUTHORS)
									// new EmployeeRestResource(req, res, getDataSource().getConnection()).searchEmployeeBySalary();
								} catch (NumberFormatException e) {
									m = new Message(
											"Wrong format for URI /statistic/coauthors/{id}: {id} is not an integer.",
											"E4A7", e.getMessage());
									res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
									m.toJSON(res.getOutputStream());
								}

								break;
							default:
								m = new Message("Unsupported operation for URI /statistic/coauthors/{id}.", "E4A5",
												String.format("Requested operation %s.", method));
								res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
								m.toJSON(res.getOutputStream());
								break;
						}
					}
					
				// the request URI is: /statistic/years/{id}
				} else if ( path.contains("years")){
					path = path.substring(path.lastIndexOf("years") + 5);
					
					if (path.length() == 0 || path.equals("/")) {
						m = new Message("Wrong format for URI /statistic/years/{id}: no {id} specified.",
										"E4A7", String.format("Requesed URI: %s.", req.getRequestURI()));
						res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
						m.toJSON(res.getOutputStream());
					} else {
						switch (method) {
							case "GET":
							
								// check that the parameter is actually an integer
								try {
									Integer.parseInt(path.substring(1));
									
									new StatisticRestResource(req, res, getDataSource().getConnection()).getYearStatistic();
								} catch (NumberFormatException e) {
									m = new Message(
											"Wrong format for URI /statistic/years/{id}: {id} is not an integer.",
											"E4A7", e.getMessage());
									res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
									m.toJSON(res.getOutputStream());
								}

								break;
							default:
								m = new Message("Unsupported operation for URI /statistic/years/{id}.", "E4A5",
												String.format("Requested operation %s.", method));
								res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
								m.toJSON(res.getOutputStream());
								break;
						}
					}
				}
			}
		} catch(Throwable t) {
			m = new Message("Unexpected error.", "E5A1", t.getMessage());
			res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			m.toJSON(res.getOutputStream());
		}

		return true;

	}
}
