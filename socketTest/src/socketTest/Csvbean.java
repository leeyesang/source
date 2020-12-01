package socketTest;

import java.util.Date;

public class Csvbean {
	
	private String comp_id; // 사업장
	private String mach_cd; // 설비코드
	private String mach_nm; // 설비명
	private String mach_size; // 규격
	private String mach_spec; // 설비명 규격
	private String capa; // 설비capacity
	private String unit; // 설비 capacity unit
	private String proc_cd; //제조공정코드
	private String mach_kind; // 설비종류
	private String mach_capct; // 용량
	private String buy_amt; // 구입금액
	private String cust_cd; // 공급업체
	private String tel_no; // 전화번호
	private String emp_no; // 담당자
	private String hp_no; // 핸드폰번호
	private String manu_cd; // 제작업체
	private String buy_date; // 구매일자
	private String use_yn; // 사용유무
	private String use_end_reason; // 사용중지사유
	private String file_nm; // 파일이름
	private String real_file_nm; // 실제파일이름
	private String set_seq; // seq
	private String tank; // 사용탱크
	private String mach_type; // 유형
	private String ip_ad; // 설비별 아이피 주소
	private String work_team; // 작업팀 구분
	private String af_mach_nm; // 유도설비명
	private String assset_no; // 자산번호
	private String insp_plan_yn; // 정기점검 계획유무
	private String model_nm; // 모델명
	private String res_use_yn; // 설비사용 유무
	private String double_chk; // 이중사출
	private String pc_ip; // pc_ip 정보
	private String mach_prod_status; // 설비생산상태
	private String reg_user_id; // 등록자
	private String reg_date; // 등록날짜
	private String upd_user_id; // 수정자
	private String upd_date; // 수정날짜
	public String getComp_id() {
		return comp_id;
	}
	public void setComp_id(String comp_id) {
		this.comp_id = comp_id;
	}
	public String getMach_cd() {
		return mach_cd;
	}
	public void setMach_cd(String mach_cd) {
		this.mach_cd = mach_cd;
	}
	public String getMach_nm() {
		return mach_nm;
	}
	public void setMach_nm(String mach_nm) {
		this.mach_nm = mach_nm;
	}
	public String getMach_size() {
		return mach_size;
	}
	public void setMach_size(String mach_size) {
		this.mach_size = mach_size;
	}
	public String getMach_spec() {
		return mach_spec;
	}
	public void setMach_spec(String mach_spec) {
		this.mach_spec = mach_spec;
	}
	public String getCapa() {
		return capa;
	}
	public void setCapa(String string) {
		this.capa = string;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getProc_cd() {
		return proc_cd;
	}
	public void setProc_cd(String proc_cd) {
		this.proc_cd = proc_cd;
	}
	public String getMach_kind() {
		return mach_kind;
	}
	public void setMach_kind(String mach_kind) {
		this.mach_kind = mach_kind;
	}
	public String getMach_capct() {
		return mach_capct;
	}
	public void setMach_capct(String mach_capct) {
		this.mach_capct = mach_capct;
	}
	public String getBuy_amt() {
		return buy_amt;
	}
	public void setBuy_amt(String string) {
		this.buy_amt = string;
	}
	public String getTel_no() {
		return tel_no;
	}
	public void setTel_no(String tel_no) {
		this.tel_no = tel_no;
	}
	public String getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}
	public String getHp_no() {
		return hp_no;
	}
	public void setHp_no(String hp_no) {
		this.hp_no = hp_no;
	}
	public String getManu_cd() {
		return manu_cd;
	}
	public void setManu_cd(String manu_cd) {
		this.manu_cd = manu_cd;
	}
	public String getBuy_date() {
		return buy_date;
	}
	public void setBuy_date(String buy_date) {
		this.buy_date = buy_date;
	}
	public String getUse_yn() {
		return use_yn;
	}
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}
	public String getUse_end_reason() {
		return use_end_reason;
	}
	public void setUse_end_reason(String use_end_reason) {
		this.use_end_reason = use_end_reason;
	}
	public String getFile_nm() {
		return file_nm;
	}
	public void setFile_nm(String file_nm) {
		this.file_nm = file_nm;
	}
	public String getReal_file_nm() {
		return real_file_nm;
	}
	public void setReal_file_nm(String real_file_nm) {
		this.real_file_nm = real_file_nm;
	}
	public String getSet_seq() {
		return set_seq;
	}
	public void setSet_seq(String set_seq) {
		this.set_seq = set_seq;
	}
	public String getTank() {
		return tank;
	}
	public void setTank(String tank) {
		this.tank = tank;
	}
	public String getMach_type() {
		return mach_type;
	}
	public void setMach_type(String mach_type) {
		this.mach_type = mach_type;
	}
	public String getIp_ad() {
		return ip_ad;
	}
	public void setIp_ad(String ip_ad) {
		this.ip_ad = ip_ad;
	}
	public String getWork_team() {
		return work_team;
	}
	public void setWork_team(String work_team) {
		this.work_team = work_team;
	}
	public String getAf_mach_nm() {
		return af_mach_nm;
	}
	public void setAf_mach_nm(String af_mach_nm) {
		this.af_mach_nm = af_mach_nm;
	}
	public String getAssset_no() {
		return assset_no;
	}
	public void setAssset_no(String assset_no) {
		this.assset_no = assset_no;
	}
	public String getInsp_plan_yn() {
		return insp_plan_yn;
	}
	public void setInsp_plan_yn(String insp_plan_yn) {
		this.insp_plan_yn = insp_plan_yn;
	}
	public String getModel_nm() {
		return model_nm;
	}
	public void setModel_nm(String model_nm) {
		this.model_nm = model_nm;
	}
	public String getRes_use_yn() {
		return res_use_yn;
	}
	public void setRes_use_yn(String res_use_yn) {
		this.res_use_yn = res_use_yn;
	}
	public String getDouble_chk() {
		return double_chk;
	}
	public void setDouble_chk(String double_chk) {
		this.double_chk = double_chk;
	}
	public String getMach_prod_status() {
		return mach_prod_status;
	}
	public void setMach_prod_status(String mach_prod_status) {
		this.mach_prod_status = mach_prod_status;
	}
	public String getPc_ip() {
		return pc_ip;
	}
	public void setPc_ip(String pc_ip) {
		this.pc_ip = pc_ip;
	}
	public String getReg_user_id() {
		return reg_user_id;
	}
	public void setReg_user_id(String reg_user_id) {
		this.reg_user_id = reg_user_id;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String string) {
		this.reg_date = string;
	}
	public String getUpd_user_id() {
		return upd_user_id;
	}
	public void setUpd_user_id(String upd_user_id) {
		this.upd_user_id = upd_user_id;
	}
	public String getUpd_date() {
		return upd_date;
	}
	public void setUpd_date(String string) {
		this.upd_date = string;
	}
	public String getCust_cd() {
		return cust_cd;
	}
	public void setCust_cd(String cust_cd) {
		this.cust_cd = cust_cd;
	}
	
	
}
