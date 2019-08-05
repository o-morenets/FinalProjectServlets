package ua.training.admission.model.entity;

public class Message {

    private Long id;
    private double averageGrade;
    private String message;
    private boolean messageRead;

    public static class Builder {

        private Long id;
        private double averageGrade;
        private String message;
        private boolean messageRead;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder averageGrade(double averageGrade) {
            this.averageGrade = averageGrade;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder messageRead(boolean messageRead) {
            this.messageRead = messageRead;
            return this;
        }

        public Message build() {
            Message message = new Message();
            message.setId(id);
            message.setAverageGrade(averageGrade);
            message.setMessage(this.message);
            message.setMessageRead(messageRead);

            return message;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isMessageRead() {
        return messageRead;
    }

    public void setMessageRead(boolean messageRead) {
        this.messageRead = messageRead;
    }
}
