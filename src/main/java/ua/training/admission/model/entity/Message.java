package ua.training.admission.model.entity;

public class Message {

    private User user;
    private double averageGrade;
    private String message;
    private boolean messageRead;

    public static class Builder {

        private User user;
        private double averageGrade;
        private String message;
        private boolean messageRead;

        public Builder user(User user) {
            this.user = user;
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
            message.setUser(user);
            message.setAverageGrade(averageGrade);
            message.setMessage(this.message);
            message.setMessageRead(messageRead);

            return message;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
