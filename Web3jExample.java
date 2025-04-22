import org.web3j.protocol.Web3j; 
import org.web3j.protocol.http.HttpService; 
import org.web3j.protocol.core.methods.response.Web3ClientVersion; 
import org.web3j.protocol.core.methods.response.EthBlockNumber; 
import org.web3j.protocol.core.methods.response.EthGetBalance; 
import org.web3j.utils.Convert; 
import java.math.BigDecimal; 
import java.math.BigInteger; 

public class Web3jExample { 
public static void main(String[] args) { 
try { 
// Connect to an Ethereum node via Infura 
String infuraUrl = "https://mainnet.infura.io/v3/YOUR_INFURA_PROJECT_ID"; 
Web3j web3 = Web3j.build(new HttpService(infuraUrl)); 
 
// Get client version 
Web3ClientVersion clientVersion = web3.web3ClientVersion().send(); 
System.out.println("Ethereum Client Version: " + clientVersion.getWeb3ClientVersion()); 
 
// Get latest block number 
EthBlockNumber blockNumber = web3.ethBlockNumber().send(); 
System.out.println("Latest Block Number: " + blockNumber.getBlockNumber()); 
 
// Check ETH balance of an address 
String address = "0x742d35Cc6634C0532925a3b844Bc454e4438f44e"; // Replace with any valid address 
EthGetBalance ethGetBalance = web3.ethGetBalance(address, 
org.web3j.protocol.core.DefaultBlockParameterName.LATEST).send();
BigInteger weiBalance = ethGetBalance.getBalance(); 
BigDecimal ethBalance = Convert.fromWei(new BigDecimal(weiBalance), Convert.Unit.ETHER); 
System.out.println("ETH Balance of " + address + ": " + ethBalance + " ETH"); 
} catch (Exception e) { 
System.err.println("Error: " + e.getMessage()); 
} 
} 
}