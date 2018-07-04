package zz.hjzn.hjwallet.model;

public class GscPriceBen {


    /**
     * num : 1
     * msg : {"fqid":3,"scid":35,"bid":0,"ywm_m":"USDT","zwm_m":"USDT","ywm_s":"GSC","zwm_s":"盖世链","spj":"1.3000","dqj":"0.8500","zgj":"1.3000","zdj":"0.8328","mr_yj":"0.8328","mc_yj":"0.8427","cjl":"7,142.00","cje":"8,325.0732","rzd":"-52.94","ws":4,"tblj":"7e2-4-18-5-143.png","rzd_ys":""}
     */

    private String num;
    private MsgBean msg;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public MsgBean getMsg() {
        return msg;
    }

    public void setMsg(MsgBean msg) {
        this.msg = msg;
    }

    public static class MsgBean {
        /**
         * fqid : 3
         * scid : 35
         * bid : 0
         * ywm_m : USDT
         * zwm_m : USDT
         * ywm_s : GSC
         * zwm_s : 盖世链
         * spj : 1.3000
         * dqj : 0.8500
         * zgj : 1.3000
         * zdj : 0.8328
         * mr_yj : 0.8328
         * mc_yj : 0.8427
         * cjl : 7,142.00
         * cje : 8,325.0732
         * rzd : -52.94
         * ws : 4
         * tblj : 7e2-4-18-5-143.png
         * rzd_ys :
         */

        private int fqid;
        private int scid;
        private int bid;
        private String ywm_m;
        private String zwm_m;
        private String ywm_s;
        private String zwm_s;
        private String spj;
        private String dqj;
        private String zgj;
        private String zdj;
        private String mr_yj;
        private String mc_yj;
        private String cjl;
        private String cje;
        private String rzd;
        private int ws;
        private String tblj;
        private String rzd_ys;

        public int getFqid() {
            return fqid;
        }

        public void setFqid(int fqid) {
            this.fqid = fqid;
        }

        public int getScid() {
            return scid;
        }

        public void setScid(int scid) {
            this.scid = scid;
        }

        public int getBid() {
            return bid;
        }

        public void setBid(int bid) {
            this.bid = bid;
        }

        public String getYwm_m() {
            return ywm_m;
        }

        public void setYwm_m(String ywm_m) {
            this.ywm_m = ywm_m;
        }

        public String getZwm_m() {
            return zwm_m;
        }

        public void setZwm_m(String zwm_m) {
            this.zwm_m = zwm_m;
        }

        public String getYwm_s() {
            return ywm_s;
        }

        public void setYwm_s(String ywm_s) {
            this.ywm_s = ywm_s;
        }

        public String getZwm_s() {
            return zwm_s;
        }

        public void setZwm_s(String zwm_s) {
            this.zwm_s = zwm_s;
        }

        public String getSpj() {
            return spj;
        }

        public void setSpj(String spj) {
            this.spj = spj;
        }

        public String getDqj() {
            return dqj;
        }

        public void setDqj(String dqj) {
            this.dqj = dqj;
        }

        public String getZgj() {
            return zgj;
        }

        public void setZgj(String zgj) {
            this.zgj = zgj;
        }

        public String getZdj() {
            return zdj;
        }

        public void setZdj(String zdj) {
            this.zdj = zdj;
        }

        public String getMr_yj() {
            return mr_yj;
        }

        public void setMr_yj(String mr_yj) {
            this.mr_yj = mr_yj;
        }

        public String getMc_yj() {
            return mc_yj;
        }

        public void setMc_yj(String mc_yj) {
            this.mc_yj = mc_yj;
        }

        public String getCjl() {
            return cjl;
        }

        public void setCjl(String cjl) {
            this.cjl = cjl;
        }

        public String getCje() {
            return cje;
        }

        public void setCje(String cje) {
            this.cje = cje;
        }

        public String getRzd() {
            return rzd;
        }

        public void setRzd(String rzd) {
            this.rzd = rzd;
        }

        public int getWs() {
            return ws;
        }

        public void setWs(int ws) {
            this.ws = ws;
        }

        public String getTblj() {
            return tblj;
        }

        public void setTblj(String tblj) {
            this.tblj = tblj;
        }

        public String getRzd_ys() {
            return rzd_ys;
        }

        public void setRzd_ys(String rzd_ys) {
            this.rzd_ys = rzd_ys;
        }
    }
}
