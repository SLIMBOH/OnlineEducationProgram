package com.suhao.stat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suhao.oledu.R;
import com.suhao.stat.entity.Stat;
import com.suhao.stat.feign.ClientService;
import com.suhao.stat.mapper.StatMapper;
import com.suhao.stat.service.StatService;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatServiceImpl extends ServiceImpl<StatMapper, Stat> implements StatService {

    @Autowired
    private ClientService clientService;

    @Override
    public void registerCount(String day) {

        QueryWrapper<Stat> wrapper = new QueryWrapper<>();
        wrapper.eq("date_calculated",day);
        baseMapper.delete(wrapper);

        R registerR = clientService.countRegister(day);
        Integer countRegister = (Integer)registerR.getData().get("countRegister");

        Stat stat = new Stat();
        stat.setRegisterNum(countRegister);
        stat.setDateCalculated(day);

        stat.setVideoViewNum(RandomUtils.nextInt(100,200));
        stat.setLoginNum(RandomUtils.nextInt(100,200));
        stat.setCourseNum(RandomUtils.nextInt(100,200));
        baseMapper.insert(stat);
    }

    @Override
    public Map<String, Object> getShowData(String type, String begin, String end) {

        QueryWrapper<Stat> wrapper = new QueryWrapper<>();
        wrapper.between("date_calculated",begin,end);
        wrapper.select("date_calculated",type);
        List<Stat> staList = baseMapper.selectList(wrapper);

        //因为返回有两部分数据：日期 和 日期对应数量
        //前端要求数组json结构，对应后端java代码是list集合
        //创建两个list集合，一个日期list，一个数量list
        List<String> date_calculatedList = new ArrayList<>();
        List<Integer> numDataList = new ArrayList<>();

        //遍历查询所有数据list集合，进行封装
        for (int i = 0; i < staList.size(); i++) {
            Stat stat = staList.get(i);
            date_calculatedList.add(stat.getDateCalculated());
            //封装对应数量
            switch (type) {
                case "login_num":
                    numDataList.add(stat.getLoginNum());
                    break;
                case "register_num":
                    numDataList.add(stat.getRegisterNum());
                    break;
                case "video_view_num":
                    numDataList.add(stat.getVideoViewNum());
                    break;
                case "course_num":
                    numDataList.add(stat.getCourseNum());
                    break;
                default:
                    break;
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("date_calculatedList",date_calculatedList);
        map.put("numDataList",numDataList);
        return map;
    }

}
