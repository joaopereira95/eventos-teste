package br.com.eventmanager.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

public class Problem {
    private Integer status;
    private OffsetDateTime timestamp;
    private String type;
    private String title;
    private String detail;
    private List<Object> objects;

    private Problem(Integer status, OffsetDateTime timestamp, String type, String title, String detail, List<Object> objects) {
        this.status = status;
        this.timestamp = timestamp;
        this.type = type;
        this.title = title;
        this.detail = detail;
        this.objects = objects;
    }

    public static ProblemBuilder builder() {
        return new ProblemBuilder();
    }

    public Integer getStatus() {
        return this.status;
    }

    public OffsetDateTime getTimestamp() {
        return this.timestamp;
    }

    public String getType() {
        return this.type;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDetail() {
        return this.detail;
    }

    public List<Object> getObjects() {
        return this.objects;
    }
    
    public static class Object {
    	
    	public static class ObjectBuilder {
    		private String name;
    		
    		public Object.ObjectBuilder name(String name) {
    			this.name = name;
    			return this;
    		}
    		
    		public Object build() {
    			return new Object(this.name);
    		}
    	}
    	
    	public static ObjectBuilder builder() {
    		return new ObjectBuilder();
    	}
		
		private String name;
		
		Object(String name) {
    		this.name = name;
    	}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
	}

    public static class ProblemBuilder {
        private Integer status;
        private OffsetDateTime timestamp;
        private String type;
        private String title;
        private String detail;
        private List<Object> objects;

        ProblemBuilder() {
        }

        public Problem.ProblemBuilder status(Integer status) {
            this.status = status;
            return this;
        }

        public Problem.ProblemBuilder timestamp(OffsetDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Problem.ProblemBuilder type(String type) {
            this.type = type;
            return this;
        }

        public Problem.ProblemBuilder title(String title) {
            this.title = title;
            return this;
        }

        public Problem.ProblemBuilder detail(String detail) {
            this.detail = detail;
            return this;
        }

        public Problem.ProblemBuilder objects(List<Object> objects) {
            this.objects = objects;
            return this;
        }

        public Problem build() {
            return new Problem(this.status, this.timestamp, this.type, this.title, this.detail, this.objects);
        }

        public String toString() {
            return "Problem.ProblemBuilder(status=" + this.status + ", timestamp=" + this.timestamp + ", type=" + this.type + ", title=" + this.title + ", detail=" + this.detail + ", objects=" + this.objects + ")";
        }
    }

    public String toString() {
        return "Problem(status=" + this.getStatus() + ", timestamp=" + this.getTimestamp() + ", type=" + this.getType() + ", title=" + this.getTitle() + ", detail=" + this.getDetail() + ", objects=" + this.getObjects() + ")";
    }


}