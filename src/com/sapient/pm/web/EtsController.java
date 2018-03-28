package com.sapient.pm.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sapient.pm.model.Trader;
import com.sapient.pm.repository.JdbcRepository;



@WebServlet(urlPatterns = { "/load-trader", "/load-portfolio" })
public class EtsController extends HttpServlet {
	JdbcRepository jdbcRepository = new JdbcRepository();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("helo frnds");
		String uri = req.getRequestURI();
		System.out.println("20");
		String path = uri.substring(uri.lastIndexOf('/'));
		System.out.println("10");
		if (path.equals("/load-trader")) {
			System.out.println("11");
			String traderId = req.getParameter("traderId");
			String ticker = req.getParameter("ticker");
			String quantity = req.getParameter("quantity");
//			String price = req.getParameter("price");
			String side = req.getParameter("side");
			System.out.println("12");
//			System.out.println(ticker + " " + quantity + " " + price + " " + side);
//			System.out.println("3");
			Trader trader = new Trader();
			System.out.println("Trader Id:"+traderId);
			int tradeId=Integer.parseInt(traderId);
			trader.setTraderId(tradeId);
			trader.setTicker(ticker);
			trader.setQuantity(Integer.parseInt(quantity));
//			trader.setPrice(Double.parseDouble(price));
			trader.setSide(side);
			System.out.println("13");
			jdbcRepository.save(trader);
			// resp.sendRedirect("secondpage.jsp");
			// req.setAttribute("traders", traders);
			System.out.println("5");
			System.out.println(req);
			System.out.println(resp);
			List<Trader> traders = jdbcRepository.findAll();
			req.setAttribute("traders", traders);
			req.getRequestDispatcher("secondpage.jsp").forward(req, resp);

		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		String path = uri.substring(uri.lastIndexOf('/'));
		System.out.println("1");
		System.out.println("Hello frends");
		if (path.equals("/load-portfolio")) {
			List<Trader> traders = jdbcRepository.findAll();
			req.setAttribute("traders", traders);
			req.getRequestDispatcher("secondpage.jsp").forward(req, resp);

		}

		System.out.println("2");
	}

}
