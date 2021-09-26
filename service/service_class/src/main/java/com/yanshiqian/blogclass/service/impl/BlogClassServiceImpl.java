package com.yanshiqian.blogclass.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yanshiqian.blogclass.entity.BlogChapter;
import com.yanshiqian.blogclass.entity.BlogClass;
import com.yanshiqian.blogclass.mapper.BlogClassMapper;
import com.yanshiqian.blogclass.service.BlogClassService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yanshiqian.servicebase.exceptionHandler.GuliException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author yanshiqian
 * @since 2021-08-14
 */
@Service
public class BlogClassServiceImpl extends ServiceImpl<BlogClassMapper, BlogClass> implements BlogClassService {

    @Override
    public List<BlogClass> getAllChapterClass() {
        QueryWrapper<BlogClass> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        List<BlogClass> blogList = baseMapper.selectList(wrapper);
        //2 把查询所有菜单list集合按照要求进行封装
        List<BlogClass> resultList = bulidBlogClass(blogList);
        return resultList;
    }

    @Override
    public List<BlogClass> getClassById(String id) {
        return null;
    }

    @Override
    public void deleteClassById(String id) {

        QueryWrapper<BlogClass> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        BlogClass blogClass = baseMapper.selectOne(queryWrapper);
        if("0".equals(blogClass.getParentId())){
             removeChildByIdGuli(id);
        }else{
           baseMapper.deleteById(id);
        }

    }
    //============递归删除菜单==================================
    public void removeChildByIdGuli(String id) {
        //1 创建list集合，用于封装所有删除菜单id值
        List<String> idList = new ArrayList<>();
        //2 向idList集合设置删除菜单id
        this.selectPermissionChildById(id,idList);
        //把当前id封装到list里面
        idList.add(id);
        baseMapper.deleteBatchIds(idList);
    }

    //2 根据当前菜单id，查询菜单里面子菜单id，封装到list集合
    private void selectPermissionChildById(String id, List<String> idList) {
        //查询菜单里面子菜单id
        QueryWrapper<BlogClass>  wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",id);
        wrapper.select("id");
        List<BlogClass> childIdList = baseMapper.selectList(wrapper);
        //把childIdList里面菜单id值获取出来，封装idList里面，做递归查询
        childIdList.stream().forEach(item -> {
            //封装idList里面
            idList.add(item.getId());
            //递归查询
            this.selectPermissionChildById(item.getId(),idList);
        });
    }

    public static List<BlogClass> bulidBlogClass(List<BlogClass> blogList) {
        //创建list集合，用于数据最终封装
        List<BlogClass> finalNode = new ArrayList<>();
        //把所有菜单list集合遍历，得到顶层菜单 pid=0菜单，设置level是1
        for(BlogClass blogClassNode : blogList) {
            //得到顶层菜单 pid=0菜单
            if("0".equals(blogClassNode.getParentId())) {
                //设置顶层菜单的level是1
                blogClassNode.setLevel(1);
                //根据顶层菜单，向里面进行查询子菜单，封装到finalNode里面
                finalNode.add(selectChildren(blogClassNode,blogList));
            }
        }
        return finalNode;
    }

    public static BlogClass selectChildren(BlogClass blogClassNode, List<BlogClass> blogList) {
        //1 因为向一层菜单里面放二层菜单，二层里面还要放三层，把对象初始化
        blogClassNode.setChildren(new ArrayList<BlogClass>());

        //2 遍历所有菜单list集合，进行判断比较，比较id和pid值是否相同
        for(BlogClass it : blogList) {
            //判断 id和pid值是否相同
            if(blogClassNode.getId().equals(it.getParentId())) {
                //把父菜单的level值+1
                int level = blogClassNode.getLevel()+1;
                it.setLevel(level);
                //如果children为空，进行初始化操作
                if(blogClassNode.getChildren() == null) {
                    blogClassNode.setChildren(new ArrayList<BlogClass>());
                }
                //把查询出来的子菜单放到父菜单里面
                blogClassNode.getChildren().add(selectChildren(it,blogList));
            }
        }
        return blogClassNode;
    }
}

