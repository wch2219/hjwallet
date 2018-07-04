package zz.hjzn.hjwallet.model;

import java.io.Serializable;
import java.util.List;

public class ParitiesMode implements Serializable{

    /**
     * reason : 查询成功
     * result : {"update":"2018-07-04 11:04:58","list":[["美元","100","661.45","656.07","664.26","665.95"],["港币","100","84.34","83.67","84.68","84.9"],["日元","100","5.9843","5.7984","6.0283","6.0294"],["欧元","100","770.87","746.92","776.56","776.65"],["英镑","100","872.49","845.38","878.91","878.97"]]}
     * error_code : 0
     */

    private String reason;
    private ResultBean result;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean implements Serializable {
        /**
         * update : 2018-07-04 11:04:58
         * list : [["美元","100","661.45","656.07","664.26","665.95"],["港币","100","84.34","83.67","84.68","84.9"],["日元","100","5.9843","5.7984","6.0283","6.0294"],["欧元","100","770.87","746.92","776.56","776.65"],["英镑","100","872.49","845.38","878.91","878.97"]]
         */

        private String update;
        private List<List<String>> list;

        public String getUpdate() {
            return update;
        }

        public void setUpdate(String update) {
            this.update = update;
        }

        public List<List<String>> getList() {
            return list;
        }

        public void setList(List<List<String>> list) {
            this.list = list;
        }
    }
}
