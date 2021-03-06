package de.bit.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.LocalTime;

import de.bit.common.Constants;

/**
 * Event Entity - entry in timesheet
 * 
 * @author christian.laboranowitsch@bridging-it.de
 * 
 */
@Entity
@Table(name = "event_tbl")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long   id;

    @NotBlank
    private String name;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_time")
    private Date   startTime;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_time")
    private Date   endTime;

/**
 * Helper method for showing the ending time on the JSF pages. Annotated wit @NonNull
 * for input validation.
 * 
 * @return {@link #endTime} as {@link LocalTime}
 */
    @NotNull
    public LocalTime getEndTimeLocal() {
        if (endTime == null) {
            return null;
        }
        return LocalTime.fromDateFields(endTime);
    }

	/**
	 * Helper method for setting the time entered into the JSF page into the
	 * current {@link #endTime}.
	 * 
	 * @param endTime
	 */
	public void setEndTimeLocal(final LocalTime endTime) {
  		if (endTime == null) {
  			this.endTime = null;
  			return;
  		}
  		Calendar cal = Calendar.getInstance();
  		cal.setTime(this.endTime);
  		cal.set(Calendar.MINUTE, endTime.getMinuteOfHour());
  		cal.set(Calendar.HOUR_OF_DAY, endTime.getHourOfDay());
  		this.endTime = cal.getTime();
	}
	/**
	 * Helper method for showing the starting time on the JSF pages. Annotated
	 * wit @NonNull for input validation.
	 * 
	 * @return {@link #startTime} as {@link LocalTime}
	 */
	@NotNull
	public LocalTime getStartTimeLocal() {
		if (startTime == null) {
			return null;
		}
		return LocalTime.fromDateFields(startTime);
	}
	
	/**
	 * Helper method for setting the time entered into the JSF page into the
	 * current {@link #startTime}.
	 * 
	 * @param endTime
	 */
	public void setStartTimeLocal(final LocalTime startTime) {
		if (startTime == null) {
			this.startTime = null;
			return;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(this.startTime);
		cal.set(Calendar.MINUTE, startTime.getMinuteOfHour());
		cal.set(Calendar.HOUR_OF_DAY, startTime.getHourOfDay());
		this.startTime = cal.getTime();
    }
	
	/**
	 * @return the starting time as formatted string
	 */
	public String getStartTimeStr() {
		return getStartTimeLocal().toString(Constants.TIME_FORMATTER);
	}
	
	/**
	 * @return the ending time as formatted string
	 */
	public String getEndTimeStr() {
		return getEndTimeLocal().toString(Constants.TIME_FORMATTER);
	}
	
	/**
	 * @return the start date as formatted string
	 */
    public String getDateStr() {
        return new SimpleDateFormat(Constants.UI_DATE_FORMAT_STR).format(startTime); //NOPMD
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Date getStartTime() {
        return startTime == null ? null : (Date) startTime.clone();
    }

    public void setStartTime(final Date startTime) {
        this.startTime = (Date) startTime.clone();
    }

    public Date getEndTime() {
        return endTime == null ? null : (Date) endTime.clone();
    }

    public void setEndTime(final Date endTime) {
        this.endTime = (Date) endTime.clone();
    }

    public DateTime startDateTime() {
        return new DateTime(startTime);
    }

    public DateTime endDateTime() {
        return new DateTime(endTime);
    }

    public LocalTime getStartDateTime() {
        return new DateTime(startTime).toLocalTime();
    }

    public LocalTime getEndDateTime() {
        return new DateTime(endTime).toLocalTime();
    }

    public long durationHours() {
        return new Duration(new DateTime(startTime), new DateTime(endTime)).getStandardHours();
    }

    public long durationMinutes() {
        return new Duration(new DateTime(startTime), new DateTime(endTime)).getStandardMinutes();
    }

    @Override
    public int hashCode() {
        final int prime = 31; //NOPMD
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Event other = (Event) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(); //NOPMD
        builder.append("Event [id=").append(id).append(", name=").append(name).append(", startTime=").append(startTime)
                .append(", endTime=").append(endTime).append("]"); //NOPMD
        return builder.toString();
    }

}
