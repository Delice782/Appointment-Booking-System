                                                          
public Appointment(String description, LocalDateTime dateTime) {
        this.description = description; 
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "Appointment [description=" + description + ", dateTime=" + dateTime + "]";
    }
}

