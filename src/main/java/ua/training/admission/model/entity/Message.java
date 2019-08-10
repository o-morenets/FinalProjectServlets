package ua.training.admission.model.entity;

/**
 * Represents a Message Entity
 *
 * @author Oleksii Morenets
 */
public class Message {

    private User user;
    private double averageGrade;
    private Boolean entered;
    private Boolean messageRead;

    public static class Builder {

        private User user;
        private double averageGrade;
        private Boolean entered;
        private Boolean messageRead;

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Builder averageGrade(double averageGrade) {
            this.averageGrade = averageGrade;
            return this;
        }

        public Builder entered(Boolean entered) {
            this.entered = entered;
            return this;
        }

        public Builder messageRead(Boolean messageRead) {
            this.messageRead = messageRead;
            return this;
        }

        public Message build() {
            Message message = new Message();
            message.setUser(user);
            message.setAverageGrade(averageGrade);
            message.setEntered(entered);
            message.setMessageRead(messageRead);

            return message;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    // Getters & Setters

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

    public Boolean getEntered() {
        return entered;
    }

    public void setEntered(Boolean entered) {
        this.entered = entered;
    }

    public Boolean getMessageRead() {
        return messageRead;
    }

    public void setMessageRead(Boolean messageRead) {
        this.messageRead = messageRead;
    }
}
