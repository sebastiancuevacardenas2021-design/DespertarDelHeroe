
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class RankingXML {

    static final String ARCHIVO = "ranking.xml";

    // Guardar las puntuaciones en el XML
    static void guardar(String[] nombres, int[] puntos, int cantidad) {
        try {
            DocumentBuilder builder = DocumentBuilderFactory
                    .newInstance().newDocumentBuilder();

            Document documento = builder.newDocument();
            Element ranking = documento.createElement("ranking");
            documento.appendChild(ranking);

            for (int i = 0; i < cantidad; i++) {
                Element jugador = documento.createElement("jugador");

                Element nombre = documento.createElement("nombre");
                nombre.setTextContent(nombres[i]);

                Element puntuacion = documento.createElement("puntos");
                puntuacion.setTextContent(String.valueOf(puntos[i]));

                jugador.appendChild(nombre);
                jugador.appendChild(puntuacion);
                ranking.appendChild(jugador);
            }

            Transformer transformer = TransformerFactory
                    .newInstance().newTransformer();
            transformer.setOutputProperty("indent", "yes");

            transformer.transform(
                    new DOMSource(documento),
                    new StreamResult(new File(ARCHIVO))
            );

            System.out.println("Ranking guardado correctamente.");

        } catch (Exception e) {
            System.out.println("Error al guardar el ranking: " + e.getMessage());
        }
    }

    // Leer las puntuaciones guardadas
    static int cargar(String[] nombres, int[] puntos) {
        try {
            File archivo = new File(ARCHIVO);

            if (!archivo.exists()) {
                return 0;
            }

            DocumentBuilder builder = DocumentBuilderFactory
                    .newInstance().newDocumentBuilder();

            Document documento = builder.parse(archivo);
            NodeList jugadores = documento.getElementsByTagName("jugador");

            int cantidad = Math.min(jugadores.getLength(), nombres.length);

            for (int i = 0; i < cantidad; i++) {
                Element jugador = (Element) jugadores.item(i);

                nombres[i] = jugador.getElementsByTagName("nombre")
                        .item(0).getTextContent();

                puntos[i] = Integer.parseInt(
                        jugador.getElementsByTagName("puntos")
                                .item(0).getTextContent()
                );
            }

            return cantidad;

        } catch (Exception e) {
            System.out.println("Error al leer el ranking: " + e.getMessage());
            return 0;
        }
    }
        // Actualiza la tabla del ranking en HTML
    static void actualizarHTML(String[] nombres, int[] puntos, int cantidad) {
        try {
            java.nio.file.Path ruta = java.nio.file.Paths.get("ranking.html");

            String html = java.nio.file.Files.readString(ruta);

            StringBuilder filas = new StringBuilder();

            if (cantidad == 0) {
                filas.append("<tr><td colspan=\"3\">No hay puntuaciones</td></tr>");
            }

            for (int i = 0; i < cantidad; i++) {
                String nombreSeguro = nombres[i]
                        .replace("&", "&amp;")
                        .replace("<", "&lt;")
                        .replace(">", "&gt;")
                        .replace("\"", "&quot;");

                filas.append("<tr><td>")
                     .append(i + 1)
                     .append("</td><td>")
                     .append(nombreSeguro)
                     .append("</td><td>")
                     .append(puntos[i])
                     .append("</td></tr>");
            }

            html = html.replaceAll(
                "(?s)(<tbody id=\"ranking\">).*?(</tbody>)",
                "$1\n" + java.util.regex.Matcher.quoteReplacement(filas.toString()) + "\n$2"
            );

            java.nio.file.Files.writeString(ruta, html);

            System.out.println("Ranking HTML actualizado.");

        } catch (Exception e) {
            System.out.println("Error al actualizar HTML: " + e.getMessage());
        }
    
    }

}
