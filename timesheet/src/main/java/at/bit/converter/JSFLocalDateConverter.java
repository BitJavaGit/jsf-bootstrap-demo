/**
 * 
 */
package at.bit.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

import at.bit.common.DateHelper;
import at.bit.common.ITimesheetConstants;

/**
 * Converts JSF input to DateTime and vice versa
 * 
 * @author christian.laboranowitsch@bridging-it.de
 *
 */
@FacesConverter("at.bit.JsfLocalDateConverter")
public class JSFLocalDateConverter implements Converter, ITimesheetConstants {

	/* (non-Javadoc)
	 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.String)
	 */
	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent comp, String string)
			throws ConverterException {
		return DateHelper.convertLocalDateFromUiString(string);
	}

	/* (non-Javadoc)
	 * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
	 */
	@Override
	public String getAsString(FacesContext facesContext, UIComponent comp, Object localDate)
			throws ConverterException {
		return ((LocalDate)localDate).toString(UI_DATE_TIME_FORMAT_STR);
	}

}