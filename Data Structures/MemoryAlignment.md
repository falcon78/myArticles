# Memory Alignment

- When a computer reads from or writes to a memory address, it will do this in word sized chunks (for example, 4 byte (32-bit) chunks on the MPC8360). Data alignment means putting the data at a memory offset equal to some multiple of the word size, which increases the system's performance due to the way the CPU handles memory. Most CPUs can access only memory aligned addresses.

[Source](http://www.cse.bgu.ac.il/common/download.asp?FileName=Memory%20Alignment.pdf&AppID=2&MainID=570&SecID=3014&MinID=2)
