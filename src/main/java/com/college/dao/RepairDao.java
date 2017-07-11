package com.college.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.college.dto.Operator;
import com.college.dto.RepairInfo;
import com.college.service.repairProcess.intf.RepairProcess;
import com.common.utils.db.MapRowMapper;
import com.common.utils.db.ObjectRowMapper;

@Repository
public class RepairDao 
{
	@Autowired
	@Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;
	
	public int add(RepairInfo object)
	{
		String sql = "insert into college_repair_info (e_id, o_id, status, apply_date, dscb) values (?, ?, ?, ?, ?)";
		
		return jdbcTemplate.update(sql, object.getE_id(), object.getO_id(), object.getStatus(), object.getApply_date(), object.getDscb());
	}
	
	public int update(RepairInfo object)
	{
		String sql = "update college_repair_info set %updatesql where id = ?";
		
		List<Object> objects = new ArrayList<Object>();
		StringBuffer updatesqlsb = new StringBuffer();
		
		if (null != object.getO_id() && 0 != object.getO_id())
		{
			updatesqlsb.append("o_id = ?, ");
			objects.add(object.getO_id());
		}
		if (null != object.getRepairer() && 0 != object.getRepairer())
		{
			updatesqlsb.append("repairer = ?, ");
			objects.add(object.getRepairer());
		}
		if (null != object.getStatus() && 0 != object.getStatus())
		{
			updatesqlsb.append("status = ?, ");
			objects.add(object.getStatus());
		}
		if (null != object.getApply_date() && !"".equals(object.getApply_date()))
		{
			updatesqlsb.append("apply_date = ?, ");
			objects.add(object.getApply_date());
		}
		if (null != object.getDscb() && !"".equals(object.getDscb()))
		{
			updatesqlsb.append("dscb = ?, ");
			objects.add(object.getDscb());
		}
		if (null != object.getRepair_from_date() && !"".equals(object.getRepair_from_date()))
		{
			updatesqlsb.append("repair_from_date = ?, ");
			objects.add(object.getRepair_from_date());
		}
		if (null != object.getExpect_date() && !"".equals(object.getExpect_date()))
		{
			updatesqlsb.append("expect_date = ?, ");
			objects.add(object.getExpect_date());
		}
		if (null != object.getFirst_step() && !"".equals(object.getFirst_step()))
		{
			updatesqlsb.append("first_step = ?, ");
			objects.add(object.getFirst_step());
		}
		if (null != object.getConclusion() && !"".equals(object.getConclusion()))
		{
			updatesqlsb.append("conclusion = ?, ");
			objects.add(object.getConclusion());
		}
		if (null != object.getRepair_parts() && !"".equals(object.getRepair_parts()))
		{
			updatesqlsb.append("repair_parts = ?, ");
			objects.add(object.getRepair_parts());
		}
		if (null != object.getBudget() && 0 != object.getBudget())
		{
			updatesqlsb.append("budget = ?, ");
			objects.add(object.getBudget());
		}
		if (null != object.getFinisher() && 0 != object.getFinisher())
		{
			updatesqlsb.append("finisher = ?, ");
			objects.add(object.getFinisher());
		}
		if (null != object.getRepairManager() && 0 != object.getRepairManager())
		{
			updatesqlsb.append("repairManager = ?, ");
			objects.add(object.getRepairManager());
		}
		if (null != object.getRepairManager_date() && !"".equals(object.getRepairManager_date()))
		{
			updatesqlsb.append("repairManager_date = ?, ");
			objects.add(object.getRepairManager_date());
		}
		if (null != object.getEduManager() && 0 != object.getEduManager())
		{
			updatesqlsb.append("eduManager = ?, ");
			objects.add(object.getEduManager());
		}
		if (null != object.getEduManager_date() && !"".equals(object.getEduManager_date()))
		{
			updatesqlsb.append("eduManager_date = ?, ");
			objects.add(object.getEduManager_date());
		}
		if (null != object.getAssetManager() && 0 != object.getAssetManager())
		{
			updatesqlsb.append("assetManager = ?, ");
			objects.add(object.getAssetManager());
		}
		if (null != object.getAssetManager_date() && !"".equals(object.getAssetManager_date()))
		{
			updatesqlsb.append("assetManager_date = ?, ");
			objects.add(object.getAssetManager_date());
		}
		if (null != object.getYuanzhang() && 0 != object.getYuanzhang())
		{
			updatesqlsb.append("yuanzhang = ?, ");
			objects.add(object.getYuanzhang());
		}
		if (null != object.getYuanzhang_date() && !"".equals(object.getYuanzhang_date()))
		{
			updatesqlsb.append("yuanzhang_date = ?, ");
			objects.add(object.getYuanzhang_date());
		}
		String updatesql = "";
		if (updatesqlsb.length() != 0)
		{
			updatesql = updatesqlsb.substring(0, updatesqlsb.length()-2);
		}
		sql = sql.replace("%updatesql", updatesql);
		objects.add(object.getId());
		return jdbcTemplate.update(sql, objects.toArray());
	}
	
	public List<Map<String, Object>> getObjects(List<RepairProcess> repairProcesses, Operator operator, RepairInfo object, int pageNum, int pageSize) 
	{
		String sql = "SELECT a.*, b.name, b.brand, b.model, b.shelf, b.purchase, b.belong_type, b.owner, b.address FROM college_repair_info a, college_equipment b where a.e_id = b.id %wheresql order by (case when a.status > 9 then a.status-9 else status end), a.apply_date desc limit ? , ?";
		List<Object> objects = new ArrayList<Object>();
		String whereSql = getWhereSql(object, objects);
		if (repairProcesses.size() != 0)
		{
			whereSql = whereSql + " and (";
			
			for (int i = 0; i < repairProcesses.size(); i++)
			{
				RepairProcess repairProcess = repairProcesses.get(i);
				whereSql = whereSql + "(";
				whereSql = whereSql + repairProcess.list(operator);
				
				if (i != repairProcesses.size()-1)
				{
					whereSql = whereSql + ") or ";
				}
				else
				{
					whereSql = whereSql + ") ";
				}
				
			}
			whereSql = whereSql + ") ";
		}
		
		sql = sql.replace("%wheresql", whereSql);
		objects.add((pageNum - 1) * pageSize);
		objects.add(pageSize);
		
		return jdbcTemplate.query(sql, objects.toArray(), new MapRowMapper());
	}
	
	public int countObjects(List<RepairProcess> repairProcesses, Operator operator, RepairInfo object) 
	{
		String sql = "SELECT count(*) FROM college_repair_info a, college_equipment b where a.e_id = b.id %wheresql ";
		List<Object> objects = new ArrayList<Object>();
		String whereSql = getWhereSql(object, objects);
		if (repairProcesses.size() != 0)
		{
			whereSql = whereSql + " and (";
			
			for (int i = 0; i < repairProcesses.size(); i++)
			{
				RepairProcess repairProcess = repairProcesses.get(i);
				whereSql = whereSql + "(";
				whereSql = whereSql + repairProcess.list(operator);
				
				if (i != repairProcesses.size()-1)
				{
					whereSql = whereSql + ") or ";
				}
				else
				{
					whereSql = whereSql + ") ";
				}
				
			}
			whereSql = whereSql + ") ";
		}
		
		sql = sql.replace("%wheresql", whereSql);
		
		return jdbcTemplate.queryForObject(sql, objects.toArray(), Integer.class);
	}
	
	public RepairInfo getRepairInfoById(RepairInfo object)
	{
		String sql = "SELECT a.*, b.name, b.brand, b.model, b.shelf, b.purchase, b.belong_type, b.owner, b.address  FROM college_repair_info a join college_equipment b on a.e_id = b.id where a.id = ?";
		
		return jdbcTemplate.query(sql, new ObjectRowMapper<RepairInfo>(RepairInfo.class), object.getId()).get(0);
	}
	
	private String getWhereSql(RepairInfo object, List<Object> args)
	{
		StringBuffer sb = new StringBuffer();
		if (null != object.getRepair_status() && 0 != object.getRepair_status())
		{
			if (1 == object.getRepair_status())
			{
				sb.append(" and status not in (7, 15)");
			}
			if (2 == object.getRepair_status())
			{
				sb.append(" and status in (7, 15)");
			}
		}
		if (null != object.getBelong_type() && 0 != object.getBelong_type())
		{
			sb.append(" and belong_type = ?");
			args.add(object.getBelong_type());
		}
		if (null != object.getOwner() && 0 != object.getOwner())
		{
			sb.append(" and owner = ?");
			args.add(object.getOwner());
		}
		if (null != object.getAddress() && 0 != object.getAddress())
		{
			sb.append(" and address = ?");
			args.add(object.getAddress());
		}
		if (null != object.getName() && !"".equals(object.getName()))
		{
			sb.append(" and name like ?");
			args.add("%" + object.getName() + "%");
		}
		return sb.toString();
	}
}
