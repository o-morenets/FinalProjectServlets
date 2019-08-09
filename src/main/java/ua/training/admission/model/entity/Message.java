package ua.training.admission.model.entity;

/**
 * Represents a Message Entity
 *
 * @author Oleksii Morenets
 */
public class Message {

    private User user;
    private double averageGrade;
    private boolean entered;
    private boolean messageRead;

    public static class Builder {

        private User user;
        private double averageGrade;
        private boolean entered;
        private boolean messageRead;

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Builder averageGrade(double averageGrade) {
            this.averageGrade = averageGrade;
            return this;
        }

        public Builder entered(boolean entered) {
            this.entered = entered;
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

    public boolean isEntered() {
        return entered;
    }

    public void setEntered(boolean entered) {
        this.entered = entered;
    }

    public boolean isMessageRead() {
        return messageRead;
    }

    public void setMessageRead(boolean messageRead) {
        this.messageRead = messageRead;
    }
}
