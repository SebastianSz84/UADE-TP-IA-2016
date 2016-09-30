package integracion.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "resultado")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResEntregaXMLDTO {

	@XmlElement
	private boolean resultado;
	@XmlElement
	private String message;

	public ResEntregaXMLDTO(boolean resultado, String message) {
		this.resultado = resultado;
		this.message = message;
	}

	public boolean isResultado() {
		return resultado;
	}

	public void setResultado(boolean resultado) {
		this.resultado = resultado;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean sosExitoso() {
		return resultado == true;
	}
}