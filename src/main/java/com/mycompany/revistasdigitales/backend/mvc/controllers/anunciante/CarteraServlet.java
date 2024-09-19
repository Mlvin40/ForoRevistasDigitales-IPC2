package com.mycompany.revistasdigitales.backend.mvc.controllers.anunciante;

import com.mycompany.revistasdigitales.backend.database.CarteraDB;
import com.mycompany.revistasdigitales.backend.usuarios.Usuario;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/cartera")
public class CarteraServlet extends HttpServlet {

    CarteraDB carteraDB = new CarteraDB();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el usuario de la sesión
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        // Si el usuario no está en sesión, redirigir al login
        if (usuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        double saldoCartera = carteraDB.obtenerSaldoActual(usuario.getNombreUsuario());
        request.setAttribute("saldoCartera", saldoCartera);

        // Enviar a la vista de la cartera
        RequestDispatcher dispatcher = request.getRequestDispatcher("/inicio/anunciante/recargarCartera.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el usuario de la sesión
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        // Si el usuario no está en sesión, redirigir al login
        if (usuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }

            // Obtener la cantidad de recarga del formulario
            double cantidadRecarga = Double.parseDouble(request.getParameter("cantidadRecarga"));
            if (cantidadRecarga <= 0) {
                request.setAttribute("mensaje", "La cantidad de recarga debe ser mayor a 0.");
                response.sendRedirect("cartera");
                return;
            }

            carteraDB.recargarCartera(usuario.getNombreUsuario(), cantidadRecarga);

            // Redirigir a la misma vista para ver el saldo actualizado
            response.sendRedirect("cartera");
    }
}