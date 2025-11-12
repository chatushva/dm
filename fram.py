def go_back_n_sender(frames, window_size):
    print("\n--- Go-Back-N Sender Simulation ---")
    n = len(frames)
    base = 0
    next_seq = 0

    while base < n:
        # Send up to window_size frames
        while next_seq < base + window_size and next_seq < n:
            print(f"Sent Frame: {frames[next_seq]}")
            next_seq += 1

        # Get ACK or loss input
        ack = input(f"Enter last ACK received or frame with error [{base}-{next_seq-1}]: ")

        if ack.isdigit():
            ack_num = int(ack)
            print(f"Error/loss at Frame {ack_num}, going back to {ack_num}\n")
            base = next_seq = ack_num
        elif ack.lower() == 'done':
            print("All frames sent successfully!\n")
            break
        else:
            # If ACK received for all sent frames
            base = next_seq

frames = ['F0', 'F1', 'F2', 'F3', 'F4', 'F5']
window_size = 3
go_back_n_sender(frames, window_size)

