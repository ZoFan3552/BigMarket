/**
 * @author:     zeddic
 * @description:  大营销转盘
 * @date:    2024/7/24 下午1:36
 */
 "use client"

import {queryRaffleAwardList, randomRaffle} from "@/apis/API";
import React, {useState, useRef, useEffect} from 'react'
// @ts-ignore
import {LuckyWheel} from '@lucky-canvas/react'
import {RaffleAwardType} from "@/types/RaffleAwardType";

export function LuckyWheelPage() {
    const strategyId = 10001
    const [blocks] = useState([
        {padding: '10px', background: '#869cfa', imgs: [{src: "https://bugstack.cn/images/system/blog-03.png"}]}
    ])

    const [prizes , setPrizes] = useState([])

    const queryRaffleAwardListHandler = async () => {
        const result = await queryRaffleAwardList(strategyId);
        const {code, info, data} = result;
        if (code !== "0000") {
            alert("获取奖品列表信息失败 code:" + code + " info：" + info);
            return;
        }
        const prizes = data.map((award: RaffleAwardType, index: number) => {
            const background = index % 2 === 0 ? '#e9e8fe' : '#b8c5f2';
            return {
                background: background,
                fonts: [{id: award.awardId, text: award.awardTitle, top: '15px'}]
            }
        })
        setPrizes(prizes);
    }

    const randomRaffleHandler = async () => {
        const result = await randomRaffle(strategyId);
        console.log(result);
        const {code, info, data} = result;
        if (code !== "0000") {
            alert("获取随机奖品信息失败 code:" + code + " info：" + info);
            return;
        }

        return data.awardId;
    }
    const [buttons] = useState([
        {radius: '40%', background: '#617df2'},
        {radius: '35%', background: '#afc8ff'},
        {
            radius: '30%', background: '#869cfa',
            pointer: true,
            fonts: [{text: '开始', top: '-10px'}]
        }
    ])
    const myLucky = useRef()

    useEffect(() => {
        queryRaffleAwardListHandler().then(r => {})
    }, []);
    return <div>
        <LuckyWheel
            ref={myLucky}
            width="300px"
            height="300px"
            blocks={blocks}
            prizes={prizes}
            buttons={buttons}
            onStart={() => { // 点击抽奖按钮会触发star回调
                // @ts-ignore
                myLucky.current.play()
                setTimeout(() => {
                    randomRaffleHandler().then(index => {
                        // @ts-ignore
                        myLucky.current.stop(index)
                    })
                }, 2500)
            }}
            onEnd={
                // @ts-ignore
                prize => {
                    alert('恭喜你抽到 ' + prize.fonts[0].text + ' 号奖品')
                }
            }
        />
    </div>
}
