package com.narola.onlineauctionsystem.servlet.auction;

import com.narola.onlineauctionsystem.dto.AuctionDTO;
import com.narola.onlineauctionsystem.exception.DaoException;
import com.narola.onlineauctionsystem.service.AuctionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteAuctionServlet extends HttpServlet {
    private AuctionService auctionService = new AuctionService();
    private AuctionDTO auctionDTO = new AuctionDTO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int auctionId = Integer.parseInt(req.getParameter("id"));
        try {
            auctionService.deleteAuction(auctionId);
            resp.sendRedirect(req.getContextPath() + "/viewAuction");
        } catch (DaoException e) {
            resp.sendRedirect("error.jsp");
        }
    }
}
