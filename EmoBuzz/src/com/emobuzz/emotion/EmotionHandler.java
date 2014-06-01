package com.emobuzz.emotion;

import java.util.HashMap;

public class EmotionHandler {
	public static HashMap<String, String> emoMap = new HashMap<String,String>();
	public static HashMap<String, String> idEmoMap = new HashMap<String,String>();
	public static HashMap<String, String> idToNameMap = new HashMap<String,String>();
	
	static{
		initEmoHandler();
	}
	
	private static void initEmoHandler(){

		emoMap.put("1", "data:image/png;base64,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");
		emoMap.put("2", "data:image/png;base64,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");
		emoMap.put("3", "data:image/png;base64,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");
		emoMap.put("4", "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAYAAACqaXHeAAATlUlEQVR4Xu1beaymV1l/zjnv+q33fneZlelMW6hdgRJKKS02RjYjRozyjyEhwZigYmJM/MMQ4op/EIhxCYsgxASNFtCgrQiKCZSyWWXaaaG1Mx1mOnefb//effF3nnPovbkw3juGNumVb/K7513PfM/vWc9z7qUffX6EH+H/NcR+Hnrf2+5aJq+1XOXlkqR6rqKikydZmCazKp7E63GcX0wmg/Mfe2zc/0Hvf+Adb+iVorzNVf6rhBA3K0fd4Ch1wlGyWUsRKiEdwqeua6rKgso8pzROsiyeRbPxZH06nZ6ZJek3hVRfvP5Vd66EftNVdd0sVSXqos7zNI1FNR3++p/+8/iHRsC733zbqUan/RuNTvfOZrtzImw0lj3PF0JKfqmuSiqyjJJoRtF0SsPL/UvD/vCh/nD4lx/75urn/ujt954MvPBNYbPxk14Q3hGG4XHXd8l1HFJSkVKC5+GfPCEZ4bOUsiii2aBPo/4WEbhpzC9QMNcj13NJef6IpHLBVUMTVhRFledZnMXpII5mTxXx7IGNtWf+6r2fPbP+fybgt153w9GFI4e/snDk2Mm5hQVqdVrkez65rkPC/oOujLYKEJFnlCUJJUzEFu7Ql+cWl2/2m+2e53vk4D3HARSEdwQI0DNIgsg8ihpzUc2aL0DoFHNMNtaodfgILV5zPakwpBqkFRVsDyTVGpXgsSpKvgYroNl0BtIGtLmy2l9fXXvrB77w+L/tRYD8QRc73e7v9pYPn1w6fIiWlxapt7hA84cXqbvco+7hBeoewvkyoO8t9WhxcZGWjxym5aOH6CW33ko33f6Ke46dOokplnCvh/ct8P485uoCc3NtoEudTpPaILjTwthqUMMHUUVGR06epFO33Ead3hwsoE3hfIPCXhPvdqi71MN3mOfvMn9okRbm56i30AMWaAFz4//qLcw1P/2ue65f2osAZ/eF337DjUcanbm3zuuJ5rvUXpynsN0g6SqS0mgfA9XEP6AJ6K6EKY7G5EvCcx4JpUhA48LDsYvnlSSlHP0+II0NFQWJJKOyLPF+RQQtykKbdE6yzmmuN0+Bfg9uU4c+lUpwjLAmi3cwVHg3A4SgNFfkYIQPwX18IqG6jizvJKJ/vCoC/GbrtZ35bqfdaVOz1aIQWvFCF8IrYncVAsNO01F8licxuaoFU9dfOCDyrKCCGESaCJzX5oKoFAYJwiCYyKnEfSU4EpLUDpZlMO+MnMolNwVZjqIKZOMBJgIvYcQ5rpWOviypkhAIpCiQqSq4Ry3aV20BJNVrWjDHwPfJD4FAa1SSIyULvztw8ElRkScUyYYP4RtUu7X9osbT7Ws24AFFBRREqtbH7MclCEynE2BE48ubFI8G1Lg0T34DpPo+KU2sJqasOABDeLacojAxoYAlxUlKQ1ji5eGYZpMxbQxna1dNgOs5t3haeN/VwYuFl+IKwgPa/CSEkIFPBAKEMq5RWe3LZx+0b2clkBqz1wLlOQewZDik4dpFWj93lqIoQrw5TH6nC/frkucHpFyP3cqmS6DSVsIukycR5oA71RMSMkJWimmjP7nwN4+sP3K1BEjPdU8ox2GTZ3+VrLQr5ks2WRfa8Vybzurd0dUKbzWfJhw3WHwd9UFGOurT4NIFunzpIh2/9WV07MaXUtjtEul5K5AkKvM+m3xtwCTYEdovYQlZntGxaURHV1Zo+cmzvdbSuZ99/wOPfIyZ3icBDlJdTykrvAL2qpVqQNZW+Csk2kqYL50kLFAtrOlb7c36l2m4sUonb7+Djt3yUnadzJV4rqTKqe209bYbAbLmI0N4Lnk+nLOVBO0OMs5y69hw9OF3v/GGL//B5554Yr8EKCGEx9HaBjwJ7MyZ1RWJ2ON6mrGmuNortP9q7YMAmHs8HpEXNKn3omsoi2KS7SYE12ECz1fbU4hdvNr4QtJz+CE5KUmqkuOFghuHYSALIX+eiP5w3y4gpHKt0DbS4iEprCw/WNHVFSqqbb8vSBS5IU+bNISvC11EwYfhEmkcceyocE3qFJoXLJSL47reNb+wwzashVWcZZSSNmgTKWN4p64mBggFBrbTXbWL+d0kWC38b3VmWXPQw0zG92EFpIWHv5YQvsgwFjlQUBpNjZFLhxwQJVyXU6WQlgQ7H0k7udUELIsJ5ciCsU5jKmZTk1nizL0qAmpRsdoNdku3+3Q7xVF9Je1nRnAbrKBmjtxlzjBaB+ejwRZdfvosNbpzVFYV+3bFgpU4L0ngWqGfx1hzyOEfJiaWBWeFMi8pShKaTqZ0eTSlrdGMBtN4xRYr5d4EMJu1rTMEk8Dg4+8jYjunS6mpA2gHafoeTiCkIGv6NYTJCxas0gRkKc4zfjRNUzrz0IO0dPQok+CHIac+Yl2UfFwLAXD0Z1JwQim0XMQxVTjPMecsSmgcZyz8xY1xem6Q/svVEYAEjUJD2UJDMwHs0rDYgTg1BAS+eUjsCAyJ1T4ZLZEWns3fpL8c2srg/zFqgEa7TTfecy8tIhCiGjWxQEijAPthzVjUpg7nRRGItctozAmLm42nNNzcorPfveieeGb19246MvfLH/nq+e/si4AK1MLMXPYrgDQc11rCDpuvjZB1lnOmqKUi8jGdsBYDcxSlCXwwX05/qPu5vK3y7FnhZ6MR5Zj7ZW98M3UOH6ZaW41OgUoZKxTbZiV2kCAAKmueGwEW5w5Jx6NKpeRDrACKO1xUMsvLe5I0/ygR3b0vAkpEJGgtYLYroKzsDUCQIcQyIHCcw5+V65LSBY5qEIffUhMAQc3ihctVsGnLWBMACx2gEKgmWPMvnbqeOotLVKQgs9WgyoPwLO224Rk7kjb+7TBC/UDhkUpArMjJ5bggKID1+q0IltUhP9i6c9+lcF4UGSIya8tEVViCXozYwEQsDCvG5nSwLyVJYQIeyQAEFEBp+apMgILgVDEJ7AJcwuoUiHfQdDE6VpIIwiMMmYLp++Ir5trlhUoIXijVeuWZ56aCxbnC6OCa4wOOo/ZNQJblY6SmBY7A2nTz3JSgSrFQbBmWZdLCZAkIUFTq8lkLnSTWR3HbrtZqgKO5jtY2ABYZp0AOiDmOq9q4ichKUoFj6v0r5B4u0sx1tgBZVqa40nEDJAv9rkbFLmziD6gFsr0JSLMNBJJTiNbaHWzwsX5sDN/kXXaLXPsy+54qXaq1Jdh7JLSfCuMGTAIBIMzOQzwKrjfGCFg1yAA95EyJVO6TcB0TBIXAaKQWZEivrTtRweSamqI2MasogATkxjPuLiXTCSVRrLl090NAPY2SSwkmyLlIybnlpZSywpscjOzAkbcG63ka6z4dzC0gqXBdqe2GRV0xERVgj5kMzuWC3YbT3dqF87R29kk6dPI6woxmuTuFZcQJJeMhpdMp9xsKmzZzCGnLaqDkeTAnZ5g0SSmF8qZZQTEwGEeoCaL7bc1GexIwS7OnosmE4tlMWwPnVhDApSrYBkqTyvSY5pRMZty4VEHIz5AQNlCxV7OmTfyypTUGs9jCnBgd14cACf3nFz5Px1/8Elo6cY2uD7gpmiBICszjNtok4WKu6yGwtQndGt152o49Spp0m2ZcVo/GE8ouXKTzF5+mx1ZGn7j/ic337TcGVBuD6OHJaEzj4YCauhvkufwlSqWjO2vP1vApzCyiCBqqSJAbNokEBMIxmWraRHJr5hbcGiulJIV5Pc+jIvDhai2KphE9+egj9Og3vkY3vuzldO3LX0HNhSXyGk1yPZDrgDybGiqOLfpYjzUHWKOYkqvFEMoLuj3yOvOUO98+9g+PbmZXKoTU7vMkzrJr5oNf8B3Vch3uA5qJU+78cuqKwHCE/D3u9xlIc+wGrBGlTPoSZLRPgo+FJY80mWX9bECtbLCCJrlKPHXTzXT7T/0MNXpL5DZb3GSpXMkolaDKgbY56ksiV4+gXAG11WAtqahrzi5RlFKWRNc1ZPWlx9bG39a397KA8vwkG64OJvc1w61fg+yUQdPtTocC3+MHkCU5yCRgOZ5OmIxWN8FM0GZVs5BBo41TxYTAeli4gstlBTi454JUEAZTd12XSpCHuMJdqKOnrgMxxFoVXggB8S5nJJsPbU+S6u3lsHIl3ndNyqacLaUSii1Tc73UCa4honw/LlAC+eefuPzx0HPugP/fEU1nZHqEHkdj7rwkKelAGccxzcByvdEfvbikblaZ8r8NIsJmi/cRpCO1DRgfBjkFl48uSe0CmLMuG2wJeZmT67haWJ5f4J6MEw7eJQvKXWGGLUZtY4QMQWnKLbI0K3h9kEYxxdGUvye+8747QjWQbkX58DOPrP3mT1zfe8fSJHpdO/APea5ypCQ23wzVUpRmg0Gc/felUfrQuc34az+dVG+5vax+saxqdpkKY7PTIo+gbU5nFYGRZxssHsigEqgxFLAaEJsEEU3QHWotjUmkISldGUYe53dybEoEMLLwhc4WJQc/01sscsrilCbjMQ22NvVuFY6n1I/iR/dJACMFZoO46H/60Y0PdX3nr0/OeS9qBt5c4FAjLep0lOSXLw3T1UFaTq1f0Ye+/PSH3lmL8Laq+jlezrLWgFaTzVxKbnablMpFkyIJjTt+QG5ecE8ghOtsoS/oIKA2EACJ9xhcE1SVxKitid3KxBlGwVZZ5AULn0Dr48GANtc2aH11XS+LH7z/v9bPbDe09iYgB6b2Ho3Soji9XoyJIrndC2GUQMGjneuDD55737sktWoSr4emONUpQDSFbjaaDZOi4lFKkOC63PjwG2ZTNM1guqMJPf7wf2DX5yg1ez0STqAFBxS7kd2XYFSVeS/POStxXJohhQ8xR78/qNZGswfu+9bKeyKict8WYDGzYwE0AA9QOwio7L1sR3DxgdYnHjr3+7/kqDY0/WreD/RMbS50inRMnxxXuGBSnscTqroi5YekVMCt+OE0GfzdP331d+bbjd51x5dumWuFNznSOSKVbEhBrpRC2M3UOsvLssw1CUk8TbLhNE4vrg2Tbz66Pv7KU5vxeavMHKj2T8A2CamdwL0CAfkOCwiBYoKrf/vwxfe83XM/6Pve9a4PoZTD6VEKbc62oyvNfkOtXBBR8opSwCVqvia7j69Nn1w9O5zSt1b+lYhkr+mEncALWr4bONp82ALKKk5QuAuqJtM86qdFvMM6c2BmEe+9O7w3xD56wC1gHpi74/j8rW959amPXHvqVPPQ8WM0v9CjBjKDo62Bl8k17ypXtpZPZxENtzZo5cIzdOHiKn396c33fubM5n12XmUhLGhXa7K2qHa4ZmKVNwZme7vA3qj38czUEiW/8czgsRvOtz7RCpu/GjQaHAh5w0UEJJXktYKQkslQymHtCwUIU7LPhe5tOrZawRzGbhKuLHxqCYiBbM+m6A8ZU0Bp3H9m9ZPHF9pvajbDa4PAN2U1/oWBz1mBKhAgTW5XUnJFR8pEfFeK41Z7KSDtnHIPAnaQANjP801ADUwApx8X7umLW3+OYPb+RhNW4PtsCXADJgMskLIlc223vqQgJsdzZfMVcKaHB5TQc/iRz9G8JZMAPPDYxhcurPW/2L88oOlwSHGU6KYLXMAk8qqy7W+zWcKqdc1udLk6MBnmhUgA7cggsy+d3fzjrX5/OhqMKJqNeTc41bkbQpcY6yylIom4vaaI2EpC1z1+z80LN7+ACWBEwPSRlclTT1wafnQymfDWdZqmtlFqzL9kVQiuAVzfI9/zKAzdVrvlv/OFTkBB1gr+/vTFT24ORqfjJOW2mKjJVINhg9f8jXaXmt0etbrz1Gy3qOH71HCd17/ttkPNFzABjBiYRDmNHr80/Hie6S5TQZWUnPq8ICC/1aFwDsIvLVJ7aYnaKIGbCJqh5x7FI7e80AmobSEylcKZZXluG6OC052EhMoFoHGv2ebfCewsgIT5eWo1AiEk6oHn8OPQ8/PJgFnoSYnq1UR+ZkaYwkcJTn2KTJM07M6BAF05bpKnxjccAAIYiSNLYZvrtL1exsCngpRd8Tm+S34TcaHV1DXDsRe2C2yjVLUUZh1/BU8xfJAUilzPIy/wSTlqjojUQSCgFrJmwm1Xb4cV2HNmwLqF7SC7Snmak4PgAlRXomIhJYBRADUfMRE7F+zmngCsog6CBYhK1Lnp6e/yA2nl48uC+/5laX6ZoqhrXmEeBAJISFFyFrA9bh6tdLw/oAWvza/OZFnGFWNelCsHhoAir2JugDAJxgIw2J1gs79XcFvb7DskcUSTKDnDbB2EIJgW6Jhlac2/7JQXTATZ5ibAFWIap/wHGJPRiEajWXxua/rwQSGABtN0axZDvxG0m8S8AZLGsdE4rs3GYxoP+jTAdvkAPf2N4fTBr5wfX9AcHQgLeHxltDaZTsfjwZD/smM8HAIjmgwnNMZ5f3ODtlbXaBPY2OwX314Z/cXOxutzAUHP72fhV+4++dkTR5buWj60zH/dEaLak0Lwvv4EhGxubtL6+iZ9Z2X4J586vfpnRLQBjA5EHQCUl8fxv4fe4K4iL+DrEwqCgOugOLa/4DiclE+vTz78qTNrH7cryRSgA0PAg2f7n7pX0ivjJLujPxp1XMfl+jjJi9Fwlp1+YmN231e/O/j6jp5+SgfoEwAngJcDdwM/Dtxrx9cCrwFeCfwYcAjwADpIFpABkSVCAO7urXl7f2z9PqMD+AmAZeAk8GLgJXa8FjgO9K6g+QORBXZanrdrmVsBuUX9fBHwP6w5oUlUPapLAAAAAElFTkSuQmCC");
		
		idEmoMap.put("awesome", "1");
		idEmoMap.put("inspiring", "2");
		idEmoMap.put("like", "3");
		idEmoMap.put("dislike", "4");
		
		idToNameMap.put("1", "Awesome");
		idToNameMap.put("2", "Inspiring");
		idToNameMap.put("3", "Like");
		idToNameMap.put("4", "Dislike");
		
		/*for (Entry<String, String> entry : emoMap) {
			String emoID = entry.getKey();
			String img = entry.getValue();
		}*/
	
	}
	
	public static String getEmotionImgBase64(String id){
		id = id.trim();
		
		return emoMap.get(id);
		
	}
	
	public static String getEmotionId(String emoName){
		emoName = emoName.trim().toLowerCase();
		return idEmoMap.get(emoName);
	}
	
	public static String getEmotionName(String id){
		id = id.trim();
		return idToNameMap.get(id);
	}
}