package com.plateno.o2omember.user.local;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.plateno.o2omember.dao.local.mapper.mdtapp.CustomSampleMapper;
import com.plateno.o2omember.dao.local.mapper.mdtapp.LocalMdtappDynamicSqlMapper;
import com.plateno.o2omember.dao.local.mapper.mdtapp.auto.TSampleFMapper;
import com.plateno.o2omember.dao.local.mapper.mdtapp.auto.TSamplePMapper;
import com.plateno.o2omember.entity.local.mdtapp.TSampleF;
import com.plateno.o2omember.entity.local.mdtapp.TSampleP;
import com.plateno.o2omember.user.ILocalSampleTkMapperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class LocalSampleTkMapper implements ILocalSampleTkMapperService {

    @Autowired
    private TSamplePMapper samplePMapper;
    @Autowired
    private TSampleFMapper sampleFMapper;
    @Autowired
    private CustomSampleMapper customSampleMapper;
    @Autowired
    private LocalMdtappDynamicSqlMapper localMdtappDynamicSqlMapper;

    /**
     * 【分页查询并获取分页信息】示例
     */
    @Override
    public Map<String, Object> getSamplePs() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse("2018-12-19 00:00:00");

        // 页码方式
        Page<TSampleP> page = PageHelper.startPage(1, 3);

        Example example = new Example(TSampleP.class);
        example.createCriteria()
                .andGreaterThanOrEqualTo(TSampleP.CREATE_DT, date);
        example.orderBy(TSampleP.CREATE_DT)
                .desc();

        List<TSampleP> samplePList1 = samplePMapper.selectByExample(example);

        log.info("1、=========> 分页sample，数据共有{}条，共{}页，每页显示{}条，当前第{}页。",
                page.getTotal(), page.getPages(), page.getPageSize(), page.getPageNum());

        samplePList1.forEach(sampleP -> log.info("=========> sampleP: {}", JSON.toJSONString(sampleP)));

        // 页码方式
        // page = PageHelper.startPage(2, 3);
        // 偏移量方式
        page = PageHelper.offsetPage(3, 3);

        example = new Example(TSampleP.class);
        example.createCriteria()
                .andGreaterThanOrEqualTo(TSampleP.CREATE_DT, date);

        example.orderBy(TSampleP.CREATE_DT)
                .desc();

        List<TSampleP> samplePList2 = samplePMapper.selectByExample(example);

        log.info("2、=========> 分页sample，数据共有{}条，共{}页，每页显示{}条，当前第{}页。",
                page.getTotal(), page.getPages(), page.getPageSize(), page.getPageNum());

        samplePList2.forEach(sampleP -> log.info("=========> sampleP: {}", JSON.toJSONString(sampleP)));

        Map<String, Object> resultMap = Maps.newHashMap();
        resultMap.put("page-1", samplePList1);
        resultMap.put("page-2", samplePList2);

        return resultMap;
    }

    /**
     * 【and/or嵌套查询】示例
     */
    @Override
    public Map<String, Object> getNestedSampleP() {
        // (id > ?) AND (field_1 LIKE ? OR field_2 LIKE ? OR field_2 LIKE ?) AND (field_3 LIKE ? OR field_3 LIKE ?) AND (create_dt > ?)
        Example example1 = new Example(TSampleP.class);
        example1.createCriteria()
                .andGreaterThan(TSampleP.ID, 2);

        example1.and()
                .orLike(TSampleP.FIELD_1, "%-2")
                .orLike(TSampleP.FIELD_2, "%-5")
                .orLike(TSampleP.FIELD_2, "%-6");

        example1.and()
                .orLike(TSampleP.FIELD_3, "%-5")
                .orLike(TSampleP.FIELD_3, "%-6");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse("2019-01-23");
        } catch (ParseException e) {
            log.error("{}", e.getLocalizedMessage());
        }
        example1.and()
                .andGreaterThan(TSampleP.CREATE_DT, date);

        example1.orderBy(TSampleP.CREATE_DT)
                .desc();

        PageHelper.<TSampleP>startPage(1, 5);
        List<TSampleP> samplePList1 = samplePMapper.selectByExample(example1);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("nestedResult1", samplePList1);

        /////////////////////////////////////////////////////
        // (field_1 LIKE ? AND field_2 LIKE ?) OR (field_3 LIKE ?)
        Example example2 = new Example(TSampleP.class);
        example2.createCriteria()
                .andLike(TSampleP.FIELD_1, "%-2")
                .andLike(TSampleP.FIELD_2, "%-5");

        example2.or()
                .andLike(TSampleP.FIELD_3, "%-6");

        example2.orderBy(TSampleP.CREATE_DT)
                .desc();

        PageHelper.<TSampleP>startPage(1, 5);
        List<TSampleP> samplePList2 = samplePMapper.selectByExample(example2);

        resultMap.put("nestedResult2", samplePList2);

        return resultMap;
    }

    /**
     * 【自编写语句mapper】示例
     */
    @Override
    public List<Map<String, Object>> customSample() {
        return customSampleMapper.customSampleQuery(2, 3);
    }

    /**
     * 【动态查询语句】示例
     */
    @Override
    public Map<String, Object> dynamicSqlSample() {
        String selectOneSql = "select t.id, t.field_1, t.create_dt, t.remark from t_sample_p t where t.id = 3";
        return localMdtappDynamicSqlMapper.selectOne(selectOneSql);
    }

    /**
     * 【插数据】示例
     */
    @Override
    public void insertSample() {
        TSampleP sampleP = new TSampleP();
        // id自增主键不需要设置，插入数据之后mybatis会自动回写至bean中
        // sampleP.setId(1);
        sampleP.setField1("field1-1");
        sampleP.setField2("field2-1");
        sampleP.setField3("field3-1");
        sampleP.setCreateDt(new Date());
        sampleP.setRemark("测试insert.sampleP-1");

        samplePMapper.insertSelective(sampleP);

        log.info("=========> 查看是否回写id主键：[sampleP-1.id: {}]", sampleP.getId());

        TSampleF sampleF = new TSampleF();
        // 同上
        // sampleF.setId(1);
        sampleF.setContent("这是内容-1");
        sampleF.setRemark("测试insert.sampleF-1");
        sampleF.setpId(sampleP.getId());

        sampleFMapper.insertSelective(sampleF);

        log.info("=========> 查看是否回写id主键：[sampleF-1.id: {}]", sampleF.getId());

        sampleP = new TSampleP();
        // 同上
        // sampleP.setId(2);
        sampleP.setField1("field1-2");
        sampleP.setField2("field2-2");
        sampleP.setField3("field3-2");
        sampleP.setCreateDt(new Date());
        sampleP.setRemark("测试insert.sampleP-2");

        samplePMapper.insertSelective(sampleP);

        log.info("=========> 查看是否回写id主键：[sampleP-2.id: {}]", sampleP.getId());

        sampleF = new TSampleF();
        // 同上
        // sampleF.setId(2);
        sampleF.setContent("这是内容-2");
        sampleF.setRemark("测试insert.sampleF-2");
        sampleF.setpId(sampleP.getId());

        sampleFMapper.insertSelective(sampleF);

        log.info("=========> 查看是否回写id主键：[sampleF-2.id: {}]", sampleF.getId());

        sampleP = new TSampleP();
        // 同上
        // sampleP.setId(3);
        sampleP.setField1("field1-3");
        sampleP.setField2("field2-3");
        sampleP.setField3("field3-3");
        sampleP.setCreateDt(new Date());
        sampleP.setRemark("测试insert.sampleP-3");

        samplePMapper.insertSelective(sampleP);

        log.info("=========> 查看是否回写id主键：[sampleP-3.id: {}]", sampleP.getId());

        sampleF = new TSampleF();
        // 同上
        // sampleF.setId(3);
        sampleF.setContent("这是内容-3");
        sampleF.setRemark("测试insert.sampleF-3");
        sampleF.setpId(sampleP.getId());

        sampleFMapper.insertSelective(sampleF);

        log.info("=========> 查看是否回写id主键：[sampleF-3.id: {}]", sampleF.getId());

        sampleP = new TSampleP();
        // 同上
        // sampleP.setId(4);
        sampleP.setField1("field1-4");
        sampleP.setField2("field2-4");
        sampleP.setField3("field3-4");
        sampleP.setCreateDt(new Date());
        sampleP.setRemark("测试insert.sampleP-4");

        samplePMapper.insertSelective(sampleP);

        log.info("=========> 查看是否回写id主键：[sampleP-4.id: {}]", sampleP.getId());

        sampleF = new TSampleF();
        // 同上
        // sampleF.setId(4);
        sampleF.setContent("这是内容-4");
        sampleF.setRemark("测试insert.sampleF-4");
        sampleF.setpId(sampleP.getId());

        sampleFMapper.insertSelective(sampleF);

        log.info("=========> 查看是否回写id主键：[sampleF-4.id: {}]", sampleF.getId());
    }

    /**
     * 【动态表名】示例，请优先使用“example条件动态表名设置方式”
     */
    @Override
    public void txDynamicTableSample() {
        TSampleP sampleP = new TSampleP();
        // id自增主键不需要设置，插入数据之后mybatis会自动回写至bean中
        // sampleP.setId(10);
        sampleP.setField1("field1-10");
        sampleP.setField2("field2-10");
        sampleP.setField3("field3-10");
        sampleP.setCreateDt(new Date());
        sampleP.setRemark("测试insert.sampleP-10");

        // entity实体类动态表名设置方式
        sampleP.setTableName("t_sample_p_1");

        samplePMapper.insertUseGeneratedKeys(sampleP);

        log.info("=========> record0.查看是否回写id主键：[sampleP-10.id: {}]", sampleP.getId());

        /////////////////////////////////////////////////////
        TSampleP selectSampleP = new TSampleP();
        selectSampleP.setField1("field1-10");

        // entity实体类动态表名设置方式
        selectSampleP.setTableName("t_sample_p_1");

        TSampleP selectOne = samplePMapper.selectOne(selectSampleP);

        log.info("=========> record0.动态表名查询数据：{}", JSON.toJSONString(selectOne));

        /////////////////////////////////////////////////////
        TSampleP updateSampleP = new TSampleP();
        updateSampleP.setField2("field2-10-修改后");
        updateSampleP.setField3("field3-10-修改后");
        updateSampleP.setRemark("测试insert.sampleP-10-修改后");

        // entity条件动态表名设置方式（经测试此方式在此不能生效，需要使用example条件设置方式）
        // updateSampleP.setTableName("t_sample_p_1");

        Example updateExample = new Example(TSampleP.class);
        updateExample.createCriteria()
                .andEqualTo(TSampleP.FIELD_1, "field1-10");

        // example条件动态表名设置方式（当使用updateByExample或者updateByExampleSelective时，请使用此方式）
        updateExample.setTableName("t_sample_p_1");

        int i = samplePMapper.updateByExampleSelective(updateSampleP, updateExample);

        log.info("=========> record1.动态表名更新了{}条数据", i);

        /////////////////////////////////////////////////////
        Example selectExample = new Example(TSampleP.class);
        selectExample.createCriteria()
                .andEqualTo(TSampleP.FIELD_1, "field1-10");

        // example条件动态表名设置方式
        selectExample.setTableName("t_sample_p_1");

        TSampleP selectOneByExample = samplePMapper.selectOneByExample(selectExample);

        log.info("=========> example.动态表名查询数据：{}", JSON.toJSONString(selectOneByExample));
    }

    /**
     * 【批量插数据】示例
     */
    @Override
    public void insertListSample() {
        TSampleP sampleP5 = new TSampleP();
        // id自增主键不需要设置，插入数据之后mybatis会自动回写至bean中
        // sampleP5.setId(5);
        sampleP5.setField1("field1-5");
        sampleP5.setField2("field2-5");
        sampleP5.setField3("field3-5");
        sampleP5.setCreateDt(new Date());
        sampleP5.setRemark("测试insert.sampleP-5");

        TSampleP sampleP6 = new TSampleP();
        // 同上
        // sampleP6.setId(6);
        sampleP6.setField1("field1-6");
        sampleP6.setField2("field2-6");
        sampleP6.setField3("field3-6");
        sampleP6.setCreateDt(new Date());
        sampleP6.setRemark("测试insert.sampleP-6");

        TSampleP sampleP7 = new TSampleP();
        // 同上
        // sampleP7.setId(7);
        sampleP7.setField1("field1-7");
        sampleP7.setField2("field2-7");
        sampleP7.setField3("field3-7");
        sampleP7.setCreateDt(new Date());
        sampleP7.setRemark("测试insert.sampleP-7");

        TSampleP sampleP8 = new TSampleP();
        // 同上
        // sampleP8.setId(8);
        sampleP8.setField1("field1-8");
        sampleP8.setField2("field2-8");
        sampleP8.setField3("field3-8");
        sampleP8.setCreateDt(new Date());
        sampleP8.setRemark("测试insert.sampleP-8");

        List<TSampleP> samplePList = Lists.newArrayList(sampleP5, sampleP6, sampleP7, sampleP8);

        samplePMapper.insertList(samplePList);

        log.info("=========> 查看是否回写id主键：[sampleP-5.id: {}]", sampleP5.getId());
        log.info("=========> 查看是否回写id主键：[sampleP-6.id: {}]", sampleP6.getId());
        log.info("=========> 查看是否回写id主键：[sampleP-7.id: {}]", sampleP7.getId());
        log.info("=========> 查看是否回写id主键：[sampleP-8.id: {}]", sampleP8.getId());
    }

    /**
     * 【insert：useGeneratedKeys=true，插数据】示例
     */
    @Override
    public void insertUseGeneratedKeysSample() {
        TSampleP sampleP = new TSampleP();
        // id自增主键不需要设置，插入数据之后mybatis会自动回写至bean中
        // sampleP5.setId(9);
        sampleP.setField1("field1-9");
        sampleP.setField2("field2-9");
        sampleP.setField3("field3-9");
        sampleP.setCreateDt(new Date());
        sampleP.setRemark("测试insert.sampleP-9");


        samplePMapper.insertUseGeneratedKeys(sampleP);

        log.info("=========> 查看是否回写id主键：[sampleP-9.id: {}]", sampleP.getId());
    }

    /**
     * 【更新数据】示例
     */
    @Override
    public void updateSample() {
        TSampleF sampleF = new TSampleF();
        sampleF.setId(1);
        sampleF.setContent("这是内容---修改后");
        sampleF.setRemark("测试update.sampleF-1");

        sampleFMapper.updateByPrimaryKeySelective(sampleF);
    }

    @Override
    public void deleteSample() {
        sampleFMapper.deleteByPrimaryKey(3);
        samplePMapper.deleteByPrimaryKey(3);
    }

    @Override
    public void createRollBackSample() {
        TSampleP sampleP = new TSampleP();
        // id自增主键不需要设置，插入数据之后mybatis会自动回写至bean中
        // sampleP.setId(11);
        sampleP.setField1("field1-11");
        sampleP.setField2("field2-11");
        sampleP.setField3("field3-11");
        sampleP.setCreateDt(new Date());
        sampleP.setRemark("测试insert.sampleP-11");

        samplePMapper.insertSelective(sampleP);

        log.info("=========> 查看是否回写id主键：[sampleP-10.id: {}]", sampleP.getId());

        TSampleF sampleF = new TSampleF();
        // 同上
        // sampleF.setId(11);
        sampleF.setContent("这是内容-11");
        sampleF.setRemark("测试insert.sampleF-11");
        sampleF.setpId(sampleP.getId());

        sampleFMapper.insertSelective(sampleF);

        log.info("=========> 查看是否回写id主键：[sampleF-11.id: {}]", sampleF.getId());

        throw new RuntimeException("回滚事务测试异常-mdt_app");
    }
}
