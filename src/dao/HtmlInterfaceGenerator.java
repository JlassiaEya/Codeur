package dao;


import models.Column;
import models.Table;

public class HtmlInterfaceGenerator {
	   public static  String generateHtmlInterface(Table table) {
	        StringBuilder htmlCode = new StringBuilder();
	        htmlCode.append("<!DOCTYPE html>\n<html>\n<head>\n<title>").append(table.getName()).append("</title>\n");
	        htmlCode.append("<style>\n");
	        // Ajoutez ici vos styles CSS
	        htmlCode.append("</style>\n");
	        htmlCode.append("</head>\n<body>\n");
	        htmlCode.append("<h1>").append(table.getName()).append("</h1>\n");
	        
	        // Générer le formulaire d'ajout
	        htmlCode.append("<h2>Ajouter un élément</h2>\n");
	        htmlCode.append("<form action=\"/ajouter\" method=\"post\">\n");
	        for (Column column : table.getColumns()) {
	            htmlCode.append("<label for=\"").append(column.getName()).append("\">").append(column.getName()).append(":</label><br>\n");
	            htmlCode.append("<input type=\"text\" id=\"").append(column.getName()).append("\" name=\"").append(column.getName()).append("\"><br>\n");
	        }
	        htmlCode.append("<input type=\"submit\" value=\"Ajouter\">\n");
	        htmlCode.append("</form>\n");
	        
	        // Générer le formulaire de modification
	        htmlCode.append("<h2>Modifier un élément</h2>\n");
	        htmlCode.append("<form action=\"/modifier\" method=\"post\">\n");
	        htmlCode.append("<label for=\"id\">ID de l'élément à modifier:</label><br>\n");
	        htmlCode.append("<input type=\"text\" id=\"id\" name=\"id\"><br>\n");
	        for (Column column : table.getColumns()) {
	            htmlCode.append("<label for=\"").append(column.getName()).append("\">Nouvelle valeur pour ").append(column.getName()).append(":</label><br>\n");
	            htmlCode.append("<input type=\"text\" id=\"").append(column.getName()).append("\" name=\"").append(column.getName()).append("\"><br>\n");
	        }
	        htmlCode.append("<input type=\"submit\" value=\"Modifier\">\n");
	        htmlCode.append("</form>\n");

	        // Générer le formulaire de suppression
	        htmlCode.append("<h2>Supprimer un élément</h2>\n");
	        htmlCode.append("<form action=\"/supprimer\" method=\"post\">\n");
	        htmlCode.append("<label for=\"id\">ID de l'élément à supprimer:</label><br>\n");
	        htmlCode.append("<input type=\"text\" id=\"id\" name=\"id\"><br>\n");
	        htmlCode.append("<input type=\"submit\" value=\"Supprimer\">\n");
	        htmlCode.append("</form>\n");

	        htmlCode.append("</body>\n</html>");

	        return htmlCode.toString();
	    }


}
