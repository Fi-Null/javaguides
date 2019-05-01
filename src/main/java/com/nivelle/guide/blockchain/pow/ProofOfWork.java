package com.nivelle.guide.blockchain.pow;

import com.nivelle.guide.blockchain.block.Block;
import com.nivelle.guide.blockchain.util.ByteUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.util.Strings;

import java.math.BigInteger;

/**
 * 工作量证明
 *
 * @author wangwei
 * @date 2018/02/04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class ProofOfWork {

    /**
     * 难度目标位
     */
    public static final int TARGET_BITS = 16;

    /**
     * 区块
     */
    private Block block;
    /**
     * 难度目标值
     */
    private BigInteger target;


    /**
     * 创建新的工作量证明，设定难度目标值
     * <p>
     * 对1进行移位运算，将1向左移动 (256 - TARGET_BITS) 位，得到我们的难度目标值
     *
     * @param block
     * @return
     */
    public static ProofOfWork newProofOfWork(Block block) {
        BigInteger targetValue = BigInteger.valueOf(1).shiftLeft((256 - TARGET_BITS));
        return new ProofOfWork(block, targetValue);
    }

    /**
     * 运行工作量证明，开始挖矿，找到小于难度目标值的Hash
     *
     * @return
     */
    public PowResult run() {
        long nonce = 0;
        String shaHex = "";
        long startTime = System.currentTimeMillis();
        while (nonce < Long.MAX_VALUE) {
            log.info("POW running, nonce=" + nonce);
            byte[] data = this.prepareData(nonce);
            shaHex = DigestUtils.sha256Hex(data);
            if (new BigInteger(shaHex, 16).compareTo(this.target) == -1) {
                log.info("Elapsed Time: {} seconds \n", new Object[]{(float) (System.currentTimeMillis() - startTime) / 1000});
                log.info("correct hash Hex: {} \n", new Object[]{shaHex});
                break;
            } else {
                nonce++;
            }
        }
        return new PowResult(nonce, shaHex);
    }

    /**
     * 验证区块是否有效
     *
     * @return
     */
    public boolean validate() {
        byte[] data = this.prepareData(this.getBlock().getNonce());
        return new BigInteger(DigestUtils.sha256Hex(data), 16).compareTo(this.target) == -1;
    }

    /**
     * 准备数据
     * <p>
     * 注意：在准备区块数据时，一定要从原始数据类型转化为byte[]，不能直接从字符串进行转换
     *
     * @param nonce
     * @return
     */
    private byte[] prepareData(long nonce) {
        byte[] prevBlockHashBytes = {};
        if (Strings.isNotBlank(this.getBlock().getPrevBlockHash())) {
            prevBlockHashBytes = new BigInteger(this.getBlock().getPrevBlockHash(), 16).toByteArray();
        }

        return ByteUtils.merge(
                prevBlockHashBytes,
                this.getBlock().hashTransaction(),
                ByteUtils.toBytes(this.getBlock().getTimeStamp()),
                ByteUtils.toBytes(TARGET_BITS),
                ByteUtils.toBytes(nonce)
        );
    }

}
